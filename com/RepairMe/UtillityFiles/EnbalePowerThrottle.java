package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import org.apache.log4j.Logger;

public class EnbalePowerThrottle implements Runnable {
   private String status = "";
   Logger logger = Logger.getLogger(MyLogger.class);

   public EnbalePowerThrottle(String status) {
      this.status = status;
   }

   public void run() {
      try {
         Process process;
         if (this.status.equals("Start")) {
            process = Runtime.getRuntime().exec(new String[]{"powershell.exe", "Set-ItemProperty -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\Power\\PowerThrottling' -Name 'PowerThrottlingOff' -value 1", "powershell.exe", "New-ItemProperty -Path 'HKLM:\\SOFTWARE\\Policies\\Microsoft\\Windows Defender' -Name 'DisableAntiSpyware' -value 1", "powershell.exe", "New-ItemProperty -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\GraphicsDrivers' - Name 'RmGpsPsEnablePerCpuCoreDpc' -value 1", "powershell.exe", "New-ItemProperty -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\GraphicsDrivers\\Power' - Name 'RmGpsPsEnablePerCpuCoreDpc' -value 1"});
         } else {
            process = Runtime.getRuntime().exec(new String[]{"powershell.exe", "Set-ItemProperty -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\Power\\PowerThrottling' -Name 'PowerThrottlingOff' -value 0", "powershell.exe", "New-ItemProperty -Path 'HKLM:\\SOFTWARE\\Policies\\Microsoft\\Windows Defender' -Name 'DisableAntiSpyware' -value 0", "powershell.exe", "New-ItemProperty -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\GraphicsDrivers' - Name 'RmGpsPsEnablePerCpuCoreDpc' -value 0", "powershell.exe", "New-ItemProperty -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\GraphicsDrivers\\Power' - Name 'RmGpsPsEnablePerCpuCoreDpc' -value 0"});
         }

         process.waitFor();
      } catch (Exception var2) {
         this.logger.info("77.2 : " + var2.getMessage());
      }

   }
}
