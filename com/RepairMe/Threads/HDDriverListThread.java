package com.RepairMe.Threads;

import com.RepairMe.Process.MyLogger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class HDDriverListThread implements Runnable {
   public String as = "";
   private String HDDStringList;
   Logger logger = Logger.getLogger(MyLogger.class);

   public HDDriverListThread(String as) {
      this.as = as;
   }

   public String getHDDStringList() {
      return this.HDDStringList;
   }

   public void setHDDStringList(String hDDStringList) {
      this.HDDStringList = hDDStringList;
   }

   public void run() {
      try {
         String line = "";
         int jj = Integer.parseInt(this.as);

         for(int i = 0; i < jj; ++i) {
            String[] arr = new String[]{"powershell.exe", "Get-Partition -disknumber " + jj + " | select DriveLetter"};
            Process p = Runtime.getRuntime().exec(arr);
            p.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line1 = "";

            for(int j = 0; (line1 = in.readLine()) != null; ++j) {
               if (j > 2 && !line1.trim().isEmpty()) {
                  line = line + line1 + "#";
               }
            }

            in.close();
         }

         this.HDDStringList = line;
      } catch (Exception var9) {
         this.logger.info("13.1 : " + var9.getMessage());
      }

   }
}
