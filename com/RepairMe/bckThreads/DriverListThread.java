package com.RepairMe.bckThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import org.apache.log4j.Logger;

public class DriverListThread implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void run() {
      String ssdList = "";
      String hddList = "";
      String line1 = "";

      try {
         if (!this.checkDriveSize()) {
            String[] s = this.checkDisk().split("#");

            for(int k = 0; k < s.length; ++k) {
               String[] arr;
               Process p;
               BufferedReader in;
               int j;
               if (s[k].contains("SSD")) {
                  arr = new String[]{"powershell.exe", "Get-Partition -disknumber " + Integer.parseInt(s[k].split(":")[0]) + " | select DriveLetter"};
                  p = Runtime.getRuntime().exec(arr);
                  p.waitFor();
                  in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                  j = 0;

                  for(ssdList = ""; (line1 = in.readLine()) != null; ++j) {
                     if (j > 2 && !line1.trim().isEmpty()) {
                        ssdList = ssdList + line1 + "#";
                     }
                  }

                  in.close();
               } else if (s[k].contains("HDD")) {
                  arr = new String[]{"powershell.exe", "Get-Partition -disknumber " + Integer.parseInt(s[k].split(":")[0]) + " | select DriveLetter"};
                  p = Runtime.getRuntime().exec(arr);
                  p.waitFor();
                  in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                  j = 0;

                  for(hddList = ""; (line1 = in.readLine()) != null; ++j) {
                     if (j > 2 && !line1.trim().isEmpty()) {
                        hddList = hddList + line1 + "#";
                     }
                  }

                  in.close();
               }
            }

            WriteIntoFile f = new WriteIntoFile();
            f.writeDiskIntoFile("D::" + BackServiceEncryptData.encrypt((ssdList + "@" + hddList).trim()) + "@@@");
         }
      } catch (Exception var10) {
         this.logger.info("BK 102" + var10.getMessage());
      }

   }

   public static void main(String[] args) {
      DriverListThread lt = new DriverListThread();
      lt.checkDriveSize();
   }

   private boolean checkDriveSize() {
      boolean retVal = false;
      File f2 = new File(System.getProperty("user.dir") + "\\private\\PreCheck.ini");
      File[] drives = File.listRoots();
      int i = 0;
      if (drives != null && drives.length > 0) {
         i = drives.length;
      }

      try {
         if (f2.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(f2));
            String line = "";

            while((line = br.readLine()) != null) {
               if (line.split(",").length > 1) {
                  if (!line.split(",")[0].equals(String.valueOf(i))) {
                     if (line.split(",")[0].length() == 1) {
                        (new PrintWriter(System.getProperty("user.dir") + "\\private\\PreCheck.ini")).close();
                        CheckSysStatus.writePreCheck(f2, i + line.substring(1));
                     } else if (line.split(",")[1].length() == 1) {
                        (new PrintWriter(System.getProperty("user.dir") + "\\private\\PreCheck.ini")).close();
                        CheckSysStatus.writePreCheck(f2, i + line.substring(1));
                     } else if (line.split(",")[3].length() == 1) {
                        (new PrintWriter(System.getProperty("user.dir") + "\\private\\PreCheck.ini")).close();
                        CheckSysStatus.writePreCheck(f2, i + line.substring(1));
                     }

                     retVal = false;
                  } else {
                     retVal = true;
                  }
               } else if (line.split(",").length == 1) {
                  (new PrintWriter(System.getProperty("user.dir") + "\\private\\PreCheck.ini")).close();
                  CheckSysStatus.writePreCheck(f2, i + line.substring(1));
               }
            }

            br.close();
         } else {
            f2.createNewFile();
            CheckSysStatus.writePreCheck(f2, i + ",");
            retVal = false;
         }
      } catch (Exception var7) {
         this.logger.info("BK 104:" + var7.getMessage());
      }

      return retVal;
   }

   private String checkDisk() {
      String line = "";

      try {
         Process p = Runtime.getRuntime().exec("powershell.exe Get-PhysicalDisk | Format-table DeviceID,MediaType".split("\\s+"));
         p.waitFor();
         BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
         String line1 = "";

         while((line1 = in.readLine()) != null) {
            if (!line1.isEmpty()) {
               if (line1.contains("SSD")) {
                  line = line + line1.replaceAll("  +", "|").split("|")[0] + ":SSD" + "#";
               } else if (line1.contains("HDD")) {
                  line = line + line1.replaceAll("  +", "|").split("|")[0] + ":HDD" + "#";
               }
            }
         }

         in.close();
      } catch (Exception var5) {
         this.logger.info("BK 103:" + var5.getMessage());
      }

      return line;
   }
}
