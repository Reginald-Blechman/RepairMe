package com.RepairMe.Threads;

import com.RepairMe.Process.MyLogger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class VirtualmachinePlatformthread implements Runnable {
   private String vmStatus;
   Logger logger = Logger.getLogger(MyLogger.class);

   public void run() {
      try {
         String[] command = new String[]{"cmd", "/c", "start powershell.exe WindowsOptionalFeature -Online -FeatureName VirtualMachinePlatform | Format-table State"};
         Process process = Runtime.getRuntime().exec(command);
         process.waitFor();
         BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
         String s = "";
         String finaLine = "";

         while((finaLine = in.readLine()) != null) {
            if (!finaLine.isEmpty() && finaLine.contains("State")) {
               s = finaLine.split(":")[1].trim();
               break;
            }
         }

         if (s.equals("Disabled")) {
            this.vmStatus = "OF";
         } else if (s.equals("Enabled")) {
            this.vmStatus = "ON";
         }
      } catch (Exception var6) {
         this.logger.info(var6.getMessage());
      }

   }

   public String getVmStatus() {
      return this.vmStatus;
   }

   public void setVmStatus(String vmStatus) {
      this.vmStatus = vmStatus;
   }
}
