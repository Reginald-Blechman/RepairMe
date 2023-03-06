package com.RepairMe.Process;

import com.RepairMe.bckProcess.RunMe;
import org.apache.log4j.Logger;

public class StartupThread extends Thread {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void run() {
      try {
         RunMe m = new RunMe();
         m.runAll();
      } catch (Exception var2) {
         this.logger.info("00.00" + var2.getMessage());
         var2.printStackTrace();
      }

   }
}
