package com.RepairMe.Threads;

import com.RepairMe.Process.MyLogger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class MemoryIntigrityThread implements Runnable {
   private String coreIsolationStatus;
   Logger logger = Logger.getLogger(MyLogger.class);

   public void run() {
      String s = "";

      try {
         String[] command = new String[]{"powershell.exe", "Get-ItemProperty -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\DeviceGuard\\Scenarios\\HypervisorEnforcedCodeIntegrity' | Get-ItemProperty -Name 'Enabled'"};
         Process process = Runtime.getRuntime().exec(command);
         process.waitFor();
         String finaLine = "";
         BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

         while((finaLine = in.readLine()) != null) {
            if (!finaLine.isEmpty() && finaLine.contains("Enabled")) {
               s = finaLine.split(":")[1].trim();
            }
         }

         if (s.isEmpty()) {
            String[] command1 = new String[]{"powershell.exe", "Get-Item -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\DeviceGuard\\Scenarios' | New-Item -Path 'HKLM:\\SYSTEM\\CurrentControlSet\\Control\\DeviceGuard\\Scenarios' -Name 'HypervisorEnforcedCodeIntegrity' | New-ItemProperty -Name 'Enabled' -value '1' -Force"};
            process = Runtime.getRuntime().exec(command1);
            process.waitFor();
            in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while((finaLine = in.readLine()) != null) {
               if (!finaLine.isEmpty() && finaLine.contains("Enabled")) {
                  s = finaLine.split(":")[1].trim();
               }
            }
         }

         if (s.equals("1")) {
            this.coreIsolationStatus = "DIS";
         }

         if (s.equals("0")) {
            this.coreIsolationStatus = "ENA";
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         this.logger.info("7.1 : " + var7.getMessage());
      }

   }

   public String getCoreIsolationStatus() {
      return this.coreIsolationStatus;
   }

   public void setCoreIsolationStatus(String coreIsolationStatus) {
      this.coreIsolationStatus = coreIsolationStatus;
   }
}
