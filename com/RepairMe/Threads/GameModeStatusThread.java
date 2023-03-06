package com.RepairMe.Threads;

import com.RepairMe.Process.MyLogger;
import org.apache.log4j.Logger;

public class GameModeStatusThread implements Runnable {
   private String s1 = "";
   Logger logger = Logger.getLogger(MyLogger.class);

   public GameModeStatusThread(String s) {
      this.s1 = s;
   }

   public void run() {
      try {
         String st = "";
         String[] command = new String[]{"powershell.exe", "Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\GameBar' -Name 'AutoGameModeEnabled' -Value " + st};
         if (this.s1.equals("Disable")) {
            st = "0";
         } else {
            st = "1";
         }

         Process process = Runtime.getRuntime().exec(command);
         process.waitFor();
      } catch (Exception var4) {
         this.logger.info(var4.getMessage());
      }

   }
}
