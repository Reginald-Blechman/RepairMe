package com.RepairMe.bckThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class CheckSysStatus implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   private boolean checkFrameWork() {
      String line = "";
      String line1 = "";
      boolean retVal = false;

      try {
         String[] ar = new String[]{"powershell.exe", "$PSVersionTable.PSVersion  | Format-List Major"};
         Process process = Runtime.getRuntime().exec(ar);
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

         for(int j = 0; (line = reader.readLine()) != null; ++j) {
            if (j > 1) {
               line1 = line;
               break;
            }
         }

         if ((double)Long.parseLong(line1.split(":")[1].trim()) > 4.5D) {
            retVal = true;
         } else {
            InstallWinGet get = new InstallWinGet();
            Thread t = new Thread(get);
            t.start();
         }
      } catch (Exception var10) {
         var10.printStackTrace();
         this.logger.info("0.2 : " + var10.getMessage());
      }

      return retVal;
   }

   private void makePreTestFile(String retVal) {
      File f = new File(System.getProperty("user.dir") + "\\private\\PreCheck.ini");

      try {
         if (!f.exists()) {
            f.createNewFile();
         }

         if (retVal.equals("OK")) {
            writePreCheck(f, "System Check : OK");
         } else {
            writePreCheck(f, "This Application is only for Win 10 and Win 11");
         }
      } catch (IOException var4) {
         this.logger.info("0.9 : " + var4.getMessage());
      }

   }

   public static synchronized void writePreCheck(File f, String s) {
      try {
         FileWriter fr = new FileWriter(f, true);
         fr.write(s);
         fr.close();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public String checkWinVer() {
      this.logger.info("checkWinVer");
      String retVal = "";

      try {
         File f = new File(System.getProperty("user.dir") + "\\private\\PreCheck.ini");
         if (f.exists() && f.length() > 0L) {
            FileReader ff = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(ff);

            String line;
            while((line = bufferedReader.readLine()) != null) {
               if (line.contains("OK")) {
                  retVal = "OK";
               } else {
                  retVal = this.checkWinVerInside();
               }
            }

            bufferedReader.close();
            ff.close();
         } else {
            f.createNewFile();
            retVal = this.checkWinVerInside();
         }
      } catch (Exception var6) {
         this.logger.info("0.0 : " + var6.getMessage());
      }

      return retVal;
   }

   private String checkWinVerInside() {
      String line1 = "";
      String retVal = "";
      line1 = System.getProperty("os.name");
      if (!line1.contains("10") && !line1.contains("11")) {
         retVal = "NO";
         this.makePreTestFile(retVal);
      } else if (!this.checkFrameWork()) {
         retVal = "Facing Some Problem in starting Application.";
      } else {
         retVal = "OK";
         this.logger.info("win 11 " + line1);
         this.makePreTestFile(retVal);
      }

      return retVal;
   }

   public void run() {
      this.checkWinVer();
   }
}
