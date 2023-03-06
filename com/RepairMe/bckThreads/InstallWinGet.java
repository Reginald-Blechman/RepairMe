package com.RepairMe.bckThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class InstallWinGet implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void run() {
      try {
         Process p = Runtime.getRuntime().exec("powershell.exe winget");
         p.waitFor();
         BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
         if (bf.readLine() != null) {
            this.installFrameWork();
         } else {
            p = Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\private\\winget.ps1");
            p.waitFor();
            this.installFrameWork();
         }

         File ff = new File(System.getProperty("user.dir") + "\\private\\winget.ps1");
         if (ff.exists()) {
            ff.delete();
         }
      } catch (Exception var4) {
         this.logger.info(var4.getMessage());
      }

   }

   private boolean installFrameWork() {
      boolean retVal = false;
      String command = "winget install --id Microsoft.Powershell --source winget";

      try {
         Process process = Runtime.getRuntime().exec(command);
         process.waitFor();
         if (process.exitValue() == 0) {
            retVal = true;
         } else {
            retVal = false;
         }
      } catch (Exception var4) {
         this.logger.info("0.1 : " + var4.getMessage());
      }

      return retVal;
   }
}
