package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import com.RepairMe.Threads.HDDriverListThread;
import com.RepairMe.Threads.SSDriverListThread;
import com.RepairMe.common.FacilitateRepairMe;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

public class DiskUtilitty {
   Logger logger = Logger.getLogger(MyLogger.class);

   public String chkDskCommand(String driveLetter) {
      String finalResult = "";

      try {
         String[] ar = new String[]{"cmd.exe", "/c", "start", "cmd.exe", "/K", "chkdsk " + driveLetter + ": /R /F "};
         Process p = Runtime.getRuntime().exec(ar);
         p.waitFor();
      } catch (Exception var5) {
         this.logger.info("6.1 : " + var5.getMessage());
      }

      return finalResult;
   }

   public String printData() {
      String line = "";

      try {
         File ff = new File(System.getProperty("java.io.tmpdir") + "\\DriveList.txt");
         BufferedReader br = new BufferedReader(new FileReader(ff));

         for(String d = ""; (d = br.readLine()) != null; line = line + d) {
         }

         ff.deleteOnExit();
         br.close();
      } catch (Exception var5) {
         this.logger.info("6.2 : " + var5.getMessage());
      }

      return line;
   }

   public void diskCleanup(String driveLetter) {
      try {
         Process p = Runtime.getRuntime().exec(("c:\\windows\\SYSTEM32\\cleanmgr.exe /d" + driveLetter.split(":")[0]).split("\\s+"));
         p.waitFor();
      } catch (Exception var3) {
         this.logger.info("6.4 : " + var3.getMessage());
      }

   }

   public String enableDisableDefrag() {
      String line1 = "";
      String retVal = "";

      try {
         Process pb = Runtime.getRuntime().exec("schtasks /Change /DISABLE /TN \"\\Microsoft\\Windows\\Defrag\\ScheduledDefrag\"".split("\\s+"));
         BufferedReader br = new BufferedReader(new InputStreamReader(pb.getInputStream()));

         while((line1 = br.readLine()) != null) {
            if (!line1.isEmpty()) {
               if (line1.contains("have been changed")) {
                  retVal = "Defragmentation disabled Suuccessfully";
               } else {
                  retVal = "Disabled";
               }
               break;
            }
         }

         br.close();
      } catch (Exception var5) {
         this.logger.info("6.6 : " + var5.getMessage());
      }

      return retVal;
   }

   public String enableDisableIndexing(String driveLetter) {
      String returnVal = "";

      try {
         String[] s = new String[]{"powershell.exe", "Get-WmiObject -Class Win32_Volume -Filter \\\"DriveLetter='" + driveLetter.split(":")[0] + ":'\\\" | Set-WmiInstance -Arguments @{IndexingEnabled=$False} -verbose"};
         Process p = Runtime.getRuntime().exec(s);
         p.waitFor();
         if (p.exitValue() == 0) {
            returnVal = "Indexing changed Successfully";
         } else {
            returnVal = "Your Drive is Not Responding Try After Some Time";
         }
      } catch (Exception var5) {
         this.logger.info("6.7 : " + var5.getMessage());
      }

      return returnVal;
   }

   public String chckIndexingStatusofDrive() {
      String line = "";
      String line1 = "";

      try {
         Process p = Runtime.getRuntime().exec("powershell.exe Get-WmiObject Win32_Volume | Format-List DriveLetter, IndexingEnabled".split("\\s+"));
         p.waitFor();
         BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

         while((line1 = br.readLine()) != null) {
            if (!line1.isEmpty()) {
               line = line + line1.split(":")[1] + "#";
            }
         }

         line1 = line.replaceAll("# # ", "# -# ");
      } catch (Exception var5) {
         this.logger.info("6.8 : " + var5.getMessage());
      }

      return line1;
   }

   public void defragHDDisk(String driveLetter) {
      try {
         String[] s = new String[]{"cmd", "/c", "start cmd.exe", "/K", "defrag " + driveLetter.split(":")[0] + ":"};
         Process p = Runtime.getRuntime().exec(s);
         p.waitFor();
      } catch (Exception var4) {
         var4.printStackTrace();
         this.logger.info("6.9 : " + var4.getMessage());
      }

   }

   public void optimizeSSD(String driveLetter) {
      try {
         Process p = Runtime.getRuntime().exec("fsutil behavior query disabledeletenotify".split("\\s+"));
         p.waitFor();
         BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
         String line1 = "";

         while((line1 = in.readLine()) != null) {
            if (!line1.contains("0")) {
               Runtime.getRuntime().exec("fsutil behavior set disabledeletenotify 0".split("\\s+"));
               p.waitFor();
            }
         }

         String[] ss = new String[]{"cmd", "/c", "start", "powershell.exe", "Optimize-Volume -DriveLetter " + driveLetter.split(":")[0] + " -ReTrim"};
         p = Runtime.getRuntime().exec(ss);
         p.waitFor();
      } catch (Exception var6) {
         var6.printStackTrace();
         this.logger.info("6.10 : " + var6.getMessage());
      }

   }

   public void printDriveListonFile(String as) {
      String finalList = "";

      try {
         File f = new File(System.getProperty("user.dir") + "\\private\\DriveList1.dll");
         if (!f.exists()) {
            f.createNewFile();
         } else if (f.exists()) {
            f.delete();
            f.createNewFile();
         }

         ExecutorService executor = Executors.newFixedThreadPool(2);
         HDDriverListThread t = new HDDriverListThread(as);
         executor.execute(t);
         SSDriverListThread t1 = new SSDriverListThread(as);
         executor.execute(t1);
         executor.shutdown();
         Thread.sleep(3000L);
         String hddList = t.getHDDStringList();
         String ssdList = t1.getSSDStringList();
         finalList = ssdList + "@" + hddList;
         FileWriter fr = new FileWriter(new File(System.getProperty("user.dir") + "\\private\\DriveList1.dll"));
         fr.write(finalList.trim());
         fr.close();
      } catch (Exception var10) {
         var10.printStackTrace();
         this.logger.info("6.11 : " + var10.getMessage());
      }

   }

   public String getListOfDrives() {
      String line = "";
      String retVal = "";

      try {
         File f = new File(System.getProperty("user.dir") + "\\private\\DiskFile.dll");
         FileReader fr = new FileReader(f);
         BufferedReader br = new BufferedReader(fr);

         for(String d = ""; (d = br.readLine()) != null; line = line + d) {
         }

         line = line.substring(line.indexOf("D::") + 3, line.indexOf("@@@"));
         retVal = FacilitateRepairMe.decrypt(line);
         fr.close();
      } catch (IOException var7) {
         this.logger.info("6.12 : " + var7.getMessage());
      }

      return retVal;
   }
}
