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

public class DelivOptiThread implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   private void conditionalDelete() {
      String fileName = System.getenv("WINDIR") + "\\ServiceProfiles\\NetworkService\\AppData\\Local\\Microsoft\\Windows\\DeliveryOptimization\\Cache";
      File ff = new File(fileName);

      try {
         if (ff.exists()) {
            String[] var6;
            int var5 = (var6 = ff.list()).length;

            for(int var4 = 0; var4 < var5; ++var4) {
               String f = var6[var4];
               Path file = Paths.get(fileName + "\\" + f);
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
      } catch (IOException var12) {
         this.logger.info("Delivery Message Thread 10.1.1:" + var12.getMessage());
      } catch (Exception var13) {
         this.logger.info("Delivery Message Thread 10.1.2:" + var13.getMessage());
      }

   }

   public void run() {
      try {
         String[] ar = new String[]{"powershell.exe", "Stop-Service -name DoSvc"};
         Process process = Runtime.getRuntime().exec(ar);
         process.waitFor();
         this.conditionalDelete();
         String[] s = new String[]{"powershell.exe", "Start-Service -name DoSvc"};
         process = Runtime.getRuntime().exec(s);
         process.waitFor();
      } catch (Exception var4) {
         this.logger.info("Delivery Optimization Thread 10.2: " + var4.getMessage());
      }

   }
}
