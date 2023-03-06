package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import org.apache.log4j.Logger;

public class GameUtillity {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void setPowerPlan(String planID) {
      try {
         String[] command = new String[]{"powershell.exe", "powercfg -SETACTIVE " + planID};
         Process process = Runtime.getRuntime().exec(command);
         process.waitFor();
      } catch (Exception var4) {
         this.logger.info("7.5 : " + var4.getMessage());
      }

   }

   public void vmpMode(String status) {
      try {
         String[] command = new String[]{"cmd", "/c", "start powershell.exe " + status + "-WindowsOptionalFeature -Online -FeatureName VirtualMachinePlatform -NoRestart"};
         Process process = Runtime.getRuntime().exec(command);
         process.waitFor();
      } catch (Exception var4) {
         this.logger.info("7.3 : " + var4.getMessage());
      }

   }

   public void setGamePriority() {
      try {
         String[] command = new String[]{"powershell.exe Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Multimedia\\SystemProfile\\Tasks\\Games' -Name 'GPU Priority' -Value '8'", "powershell.exe Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Multimedia\\SystemProfile\\Tasks\\Games' -Name 'Priority' -Value '6'", "powershell.exe Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Multimedia\\SystemProfile\\Tasks\\Games' -Name 'Scheduling Category' -Value 'High'", "powershell.exe Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Multimedia\\SystemProfile\\Tasks\\Games' -Name 'SFIO Priority' -Value 'High'", "powershell.exe Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Multimedia\\SystemProfile' -Name 'NetworkThrottlingIndex' -Value 'ffffffff'", "powershell.exe Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Multimedia\\SystemProfile' -Name 'SystemResponsiveness' -Value '00000000'"};

         for(int i = 0; i < 4; ++i) {
            Process process = Runtime.getRuntime().exec(command[i]);
            process.waitFor();
         }
      } catch (Exception var4) {
         this.logger.info("7.2 : " + var4.getMessage());
      }

   }

   public void revertGamePriority() {
      try {
         String[] command = new String[]{"powershell.exe Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Multimedia\\SystemProfile\\Tasks\\Games' -Name 'Priority' -Value '2'", "powershell.exe Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Multimedia\\SystemProfile\\Tasks\\Games' -Name 'Scheduling Category' -Value 'Medium'", "powershell.exe Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Multimedia\\SystemProfile\\Tasks\\Games' -Name 'SFIO Priority' -Value 'Normal'", "powershell.exe Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Multimedia\\SystemProfile' -Name 'NetworkThrottlingIndex' -Value '0000000a'", "powershell.exe Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Multimedia\\SystemProfile' -Name 'SystemResponsiveness' -Value '00000014'"};

         for(int i = 0; i < 4; ++i) {
            Process process = Runtime.getRuntime().exec(command[i]);
            process.waitFor();
         }
      } catch (Exception var4) {
         this.logger.info("7.2 : " + var4.getMessage());
      }

   }

   public void disableMemoryInteigrity(String status) {
      try {
         String[] command = new String[]{"powershell.exe", "New-ItemProperty -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\DeviceGuard\\Scenarios\\HypervisorEnforcedCodeIntegrity' -Name 'Enabled' -value '" + status + "' -Force"};
         Process process = Runtime.getRuntime().exec(command);
         process.waitFor();
      } catch (Exception var4) {
         this.logger.info("7.1 : " + var4.getMessage());
      }

   }
}
