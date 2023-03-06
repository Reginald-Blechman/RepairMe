package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class DeleteSoftDistFolderThread implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   private void conditionalDelete() {
      String fileName = System.getenv("WINDIR") + "\\SoftwareDistribution\\";
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
         this.logger.info("12.1.1:" + var12.getMessage());
      } catch (Exception var13) {
         this.logger.info("12.1.2:" + var13.getMessage());
      }

   }

   private void stopService() {
      try {
         BufferedWriter bf = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\private\\softDistFolderStop.bat"));
         bf.write("net stop wuauserv");
         bf.newLine();
         bf.write("net stop bits");
         bf.newLine();
         bf.close();
      } catch (Exception var2) {
         this.logger.info("12.2:" + var2.getMessage());
      }

   }

   private void startService() {
      try {
         BufferedWriter bf = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\private\\softDistFolderStart.bat"));
         bf.write("net start wuauserv");
         bf.newLine();
         bf.write("net start bits");
         bf.close();
      } catch (Exception var2) {
         this.logger.info("12.3:" + var2.getMessage());
      }

   }

   public void run() {
      try {
         this.stopService();
         String[] s = new String[]{System.getProperty("user.dir") + "\\private\\softDistFolderStop.bat"};
         Process process = Runtime.getRuntime().exec(s);
         process.waitFor();
         this.conditionalDelete();
         this.startService();
         String[] s1 = new String[]{System.getProperty("user.dir") + "\\private\\softDistFolderStart.bat"};
         process = Runtime.getRuntime().exec(s1);
         process.waitFor();
         FileUtils.forceDelete(new File(s[0]));
         FileUtils.forceDelete(new File(s1[0]));
      } catch (Exception var4) {
         this.logger.info("12.4" + var4.getMessage());
      }

   }
}
