package com.RepairMe.Threads;

import com.RepairMe.Process.MyLogger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class PowerPlanStatus implements Runnable {
   private String PowerPlanStatus;
   Logger logger = Logger.getLogger(MyLogger.class);

   public String getPowerPlanStatus() {
      return this.PowerPlanStatus;
   }

   public void setPowerPlanStatus(String powerPlanStatus) {
      this.PowerPlanStatus = powerPlanStatus;
   }

   public void run() {
      try {
         String[] command = new String[]{"powershell.exe", "powercfg -list"};
         Process process = Runtime.getRuntime().exec(command);
         process.waitFor();
         String finaLine = "";
         BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
         int j = 0;
         String s = "";

         while((finaLine = in.readLine()) != null) {
            if (!finaLine.isEmpty()) {
               if (j > 1) {
                  s = s + finaLine.split(":")[1].trim().replaceAll("  +", "#") + "@";
               }

               ++j;
            }
         }

         this.PowerPlanStatus = s;
      } catch (Exception var7) {
         this.logger.info(var7.getMessage());
      }

   }
}
