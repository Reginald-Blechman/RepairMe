package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import org.apache.log4j.Logger;

public class PolicyThread implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);
   String policyName = "";

   public PolicyThread(String pp) {
      this.policyName = pp;
   }

   public void privacyPolicy() {
      try {
         Process process = Runtime.getRuntime().exec(this.policyName);
         process.waitFor();
      } catch (Exception var2) {
         this.logger.info(var2.getMessage());
      }

      this.logger.info("Policies Disabled: inkingData , TailoredExperience, AdvertisingInfo , Telemetry");
   }

   public void run() {
      this.privacyPolicy();
   }
}
