package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import com.RepairMe.Threads.EnableTweaksThreads;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.log4j.Logger;

public class EnableDisableTweaks {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void oldContextMenu(String status, ArrayList<String> tweaks) {
      ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);

      try {
         EnableTweaksThreads ep;
         int i;
         if (status.equals("Enable")) {
            for(i = 0; i < tweaks.size(); ++i) {
               ep = new EnableTweaksThreads(this.commands((String)tweaks.get(i)));
               executor.execute(ep);
            }
         } else {
            for(i = 0; i < tweaks.size(); ++i) {
               ep = new EnableTweaksThreads(this.revertCommands((String)tweaks.get(i)));
               executor.execute(ep);
            }
         }

         do {
            Thread.sleep(50L);
         } while(executor.getActiveCount() != 0);

         executor.shutdown();
      } catch (Exception var6) {
         this.logger.info(var6.getMessage());
      }

   }

   private String commands(String tweakName) {
      String retVal = "";
      switch(tweakName.hashCode()) {
      case 48:
         if (tweakName.equals("0")) {
            retVal = "Unknown";
            return retVal;
         }
         break;
      case 954726253:
         if (tweakName.equals("Old Contaxt Menu")) {
            retVal = "reg.exe add \"HKCU\\Software\\Classes\\CLSID\\{86ca1aa0-34aa-4e8b-a509-50c905bae2a2}\\InprocServer32\" /f /ve";
            return retVal;
         }
      }

      retVal = "UNKNOWN";
      return retVal;
   }

   private String revertCommands(String tweakName) {
      String retVal = "";
      switch(tweakName.hashCode()) {
      case 954726253:
         if (tweakName.equals("Old Contaxt Menu")) {
            retVal = "reg.exe delete \"HKCU\\Software\\Classes\\CLSID\\{86ca1aa0-34aa-4e8b-a509-50c905bae2a2}\" /f";
            break;
         }
      default:
         retVal = "UNKNOWN";
      }

      return retVal;
   }
}
