package com.RepairMe.Threads;

import com.RepairMe.Process.MyLogger;
import org.apache.log4j.Logger;

public class EnableTweaksThreads implements Runnable {
   private String gh = "";
   Logger logger = Logger.getLogger(MyLogger.class);

   public EnableTweaksThreads(String gh) {
      this.gh = gh;
   }

   public void run() {
      try {
         if (this.gh.equals("Volume")) {
            String[] s = new String[]{"powershell.exe", "New-Item -Path 'HKLM:\\Software\\Microsoft\\Windows NT\\CurrentVersion' -Name 'MTCUVC'"};
            Process p = Runtime.getRuntime().exec(s);
            p.waitFor();
            String[] s1 = new String[]{"powershell.exe", "Set-ItemProperty -Path 'HKLM:\\Software\\Microsoft\\Windows NT\\CurrentVersion\\MTCUVC' -Name 'EnableMtcUvc' -Value 0"};
            p = Runtime.getRuntime().exec(s1);
            p.waitFor();
            String[] s2 = new String[]{"powershell.exe", "Stop-Process -name explorer"};
            p = Runtime.getRuntime().exec(s2);
            p.waitFor();
         } else {
            Process p = Runtime.getRuntime().exec(new String[]{this.gh});
            p.waitFor();
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         this.logger.info("");
      }

   }
}
