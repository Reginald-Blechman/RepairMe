package com.RepairMe.bckThreads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class RegistryWorkers implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void run() {
      this.createThrottleKey();
      this.disablePrefetch();
   }

   private void disablePrefetch() {
      try {
         Process process = Runtime.getRuntime().exec(new String[]{"powershell.exe", "Set-ItemProperty -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\Session Manager\\Memory Management\\PrefetchParameters' -Name 'EnablePrefetcher' -value 0"});
         process.waitFor();
      } catch (Exception var2) {
         this.logger.info("7.2 : " + var2.getMessage());
      }

   }

   private void createThrottleKey() {
      try {
         Process process = Runtime.getRuntime().exec(new String[]{"powershell.exe", "Get-Item -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\Power\\PowerThrottling'"});
         BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
         process.waitFor();
         if (br.readLine() == null) {
            process = Runtime.getRuntime().exec(new String[]{"powershell.exe", "New-Item -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\Power' -Name 'PowerThrottling' "});
            process.waitFor();
            process = Runtime.getRuntime().exec(new String[]{"powershell.exe", "New-ItemProperty -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\Power\\PowerThrottling' -Name 'PowerThrottlingOff' -value 0"});
            process.waitFor();
         }

         br.close();
      } catch (Exception var3) {
         this.logger.info(var3.getMessage());
      }

   }
}
