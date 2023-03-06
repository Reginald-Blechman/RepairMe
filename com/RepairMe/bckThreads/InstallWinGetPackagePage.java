package com.RepairMe.bckThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class InstallWinGetPackagePage implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void run() {
      try {
         Process p = Runtime.getRuntime().exec("powershell.exe winget");
         p.waitFor();
         BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
         if (bf.readLine() == null) {
            p = Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\private\\winget.ps1");
            p.waitFor();
         }

         File ff = new File(System.getProperty("user.dir") + "\\private\\winget.ps1");
         if (ff.exists()) {
            ff.delete();
         }
      } catch (Exception var4) {
         this.logger.info(var4.getMessage());
      }

   }
}
