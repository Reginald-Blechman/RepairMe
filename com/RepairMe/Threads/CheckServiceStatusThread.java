package com.RepairMe.Threads;

import com.RepairMe.Process.MyLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class CheckServiceStatusThread implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);
   private String serviceName;

   public CheckServiceStatusThread(String sName) {
      this.serviceName = sName;
   }

   public void run() {
      this.checkStatus();
   }

   private void writeIntoFile(String stringtoWrite) {
      try {
         FileWriter fw = new FileWriter(new File(System.getProperty("java.io.tmpdir") + "\\ServiceStatus.txt"), true);
         fw.write(stringtoWrite);
         fw.close();
      } catch (IOException var3) {
         this.logger.info(var3.getMessage());
      }

   }

   private synchronized void checkStatus() {
      try {
         String[] checkService = new String[]{"powershell.exe", " Get-Service " + this.serviceName + "  | select status"};
         Process p = Runtime.getRuntime().exec(checkService);
         BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
         String line1 = "";
         String line = "";

         for(int j = 0; (line1 = in.readLine()) != null; ++j) {
            if (j > 2 && !line1.isEmpty()) {
               if (line1.equals("Running")) {
                  line = this.serviceName + "#True@";
               } else {
                  line = this.serviceName + "#False@";
               }
            }
         }

         p.waitFor();
         this.writeIntoFile(line);
      } catch (Exception var7) {
         this.logger.info(var7.getMessage());
      }

   }
}
