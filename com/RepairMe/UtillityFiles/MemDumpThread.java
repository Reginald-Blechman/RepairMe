package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class MemDumpThread implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   private void conditionalDelete() {
      String winDirPath = System.getenv("WINDIR");
      String minidump = winDirPath + "\\Minidump\\";
      File memoryDmp = new File(winDirPath + "\\MEMORY.DMP");
      File ff = new File(minidump);

      try {
         if (ff.exists()) {
            String[] var8;
            int var7 = (var8 = ff.list()).length;

            for(int var6 = 0; var6 < var7; ++var6) {
               String f = var8[var6];
               Path file = Paths.get(minidump + "\\" + f);
               BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
               long diff = (new Date()).getTime() - attr.lastModifiedTime().toMillis();
               File dummyFile = null;
               if (diff > 86400000L) {
                  dummyFile = file.toFile();
                  if (dummyFile.isDirectory()) {
                     FileUtils.cleanDirectory(dummyFile);
                  }

                  FileUtils.forceDelete(dummyFile);
               }
            }
         }

         if (memoryDmp.exists()) {
            FileUtils.forceDelete(memoryDmp);
         }
      } catch (IOException var14) {
         this.logger.info("Delivery Message Thread 11.1.1:" + var14.getMessage());
      } catch (Exception var15) {
         this.logger.info("Delivery Message Thread 11.1.2:" + var15.getMessage());
      }

   }

   public void run() {
      this.conditionalDelete();
   }
}
