package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import org.apache.log4j.Logger;

public class ServiceDisableThread implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);
   private String action;

   public ServiceDisableThread(String action) {
      this.action = action;
   }

   public void run() {
      String[] stopService = new String[]{"powershell.exe", "Stop-Service -force -name SysMain,WSearch,DiagTrack,Themes -verbose"};
      String[] startService = new String[]{"powershell.exe", "Start-Service -force -name SysMain,WSearch,DiagTrack,Themes -verbose"};

      try {
         Process p;
         if (this.action.equals("Stop")) {
            p = Runtime.getRuntime().exec(stopService);
            p.waitFor();
         } else if (this.action.equals("Start")) {
            p = Runtime.getRuntime().exec(startService);
            p.waitFor();
         }
      } catch (Exception var4) {
         this.logger.info(var4.getMessage());
      }

   }
}
