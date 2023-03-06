package com.RepairMe.StartupTest;

import com.RepairMe.Process.MyLogger;
import com.RepairMe.Process.StartupThread;
import org.apache.log4j.Logger;

public class StartupTests {
   Logger logger = Logger.getLogger(MyLogger.class);
   private String result;

   public String getResult() {
      return this.result;
   }

   public void setResult(String result) {
      this.result = result;
   }

   public void startProcess() {
      try {
         StartupThread st = new StartupThread();
         st.start();
      } catch (Exception var2) {
         var2.getStackTrace();
         this.logger.info("0.3 : " + var2.getMessage());
      }

   }
}
