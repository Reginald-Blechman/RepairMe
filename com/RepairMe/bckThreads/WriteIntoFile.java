package com.RepairMe.bckThreads;

import java.io.File;
import java.io.FileWriter;
import org.apache.log4j.Logger;

public class WriteIntoFile {
   Logger logger = Logger.getLogger(MyLogger.class);

   public synchronized void writeIntoFile(String s) {
      try {
         File f = new File(System.getProperty("user.dir") + "\\private\\SysFile.dll");
         if (!f.exists()) {
            f.createNewFile();
         }

         FileWriter fr = new FileWriter(f, true);
         fr.write(s);
         fr.close();
      } catch (Exception var4) {
         var4.printStackTrace();
         this.logger.info("BK 109 : " + var4.getMessage());
      }

   }

   public void writeDiskIntoFile(String s) {
      try {
         File f = new File(System.getProperty("user.dir") + "\\private\\DiskFile.dll");
         if (!f.exists()) {
            f.createNewFile();
         }

         FileWriter fr = new FileWriter(f, true);
         fr.write(s);
         fr.close();
      } catch (Exception var4) {
         this.logger.info("BK 109 : " + var4.getMessage());
      }

   }
}
