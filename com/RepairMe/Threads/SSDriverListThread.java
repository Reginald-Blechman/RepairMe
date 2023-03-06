package com.RepairMe.Threads;

import com.RepairMe.Process.MyLogger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class SSDriverListThread implements Runnable {
   private String as = "";
   private String SSDStringList;
   Logger logger = Logger.getLogger(MyLogger.class);

   public SSDriverListThread(String as) {
      this.as = as;
   }

   public void run() {
      String line = "";

      try {
         int jj = Integer.parseInt(this.as);

         for(int i = 0; i < jj; ++i) {
            String[] arr = new String[]{"powershell.exe", "Get-Partition -disknumber " + i + " | select DriveLetter"};
            Process p = Runtime.getRuntime().exec(arr);
            p.waitFor();
            String line1 = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            for(int j = 0; (line1 = in.readLine()) != null; ++j) {
               if (j > 2 && !line1.trim().isEmpty()) {
                  line = line + line1 + "#";
               }
            }

            in.close();
         }

         this.SSDStringList = line;
      } catch (Exception var9) {
         this.logger.info("16.1 " + var9.getMessage());
      }

   }

   public String getSSDStringList() {
      return this.SSDStringList;
   }

   public void setSSDStringList(String sSDStringList) {
      this.SSDStringList = sSDStringList;
   }
}
