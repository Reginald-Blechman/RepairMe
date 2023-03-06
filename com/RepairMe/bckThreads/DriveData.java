package com.RepairMe.bckThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.apache.log4j.Logger;

public class DriveData implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   private void script() {
      StringBuffer br = new StringBuffer();
      br.append("$diskDrive = Get-WmiObject Win32_DiskDrive \n");
      br.append("foreach ($disk in $diskDrive) { \n");
      br.append("$diskIndex = $disk.Index \n");
      br.append("$diskModel = $disk.Model \n");
      br.append("$diskSize = \"{0:N2}\" -f ($disk.Size / 1GB) + \" GB\" \n");
      br.append("$diskSerial = $disk.SerialNumber \n");
      br.append("$diskPartition = Get-WmiObject Win32_DiskPartition | measure-object -line \n");
      br.append("foreach ($partition in $diskPartition) { \n");
      br.append("$partitionName = $partition.Name \n");
      br.append("$partitionSize = \"{0:N2}\" -f ($partition.Size / 1GB) + \" GB\" \n");
      br.append("Write-Output \"<td> $diskIndex</td><td>$diskModel</td><td>$diskSize</td><td>$diskSerial</td>\" \n");
      br.append("} } \n");

      try {
         File f = new File(System.getProperty("user.dir") + "\\private\\DiskData.ps1");
         if (!f.exists()) {
            f.createNewFile();
         }

         FileWriter fr = new FileWriter(f);
         fr.write(br.toString());
         fr.close();
      } catch (Exception var4) {
         this.logger.info("BK 109 : " + var4.getMessage());
      }

   }

   public String getStorageInfo() {
      String line = "<html><body><table border =1><tr><td>Disk</td><td>Model Name</td><td>Disk Size</td><td>Serial No</td><td>Bus Type</td><td>Sector Size</td><td>Type</td></tr>";

      try {
         this.script();
         Process p = Runtime.getRuntime().exec(new String[]{"powershell.exe", System.getProperty("user.dir") + "\\private\\DiskData.ps1"});
         p.waitFor();
         BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
         String s = "";
         int j = 1;
         HashMap restVals = this.getStorageInfo1();

         while((s = reader.readLine()) != null) {
            if (!s.isEmpty() && s.contains("<td>")) {
               line = line + "<tr>" + s + "<td>" + this.StorageType(((String)restVals.get(String.valueOf(j))).split(",")[0]) + "</td><td>" + ((String)restVals.get(String.valueOf(j))).split(",")[2] + "</td><td>" + ((String)this.checkDisk().get(String.valueOf(j + 1))).split(",")[1] + "</td></tr>";
               ++j;
            }
         }

         line = line + "</table>";
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      (new File(System.getProperty("user.dir") + "\\private\\DiskData.ps1")).delete();
      return "S::" + BackServiceEncryptData.encrypt(line + "<br><br>" + this.driveDetails()) + "---";
   }

   private HashMap<String, String> checkDisk() {
      HashMap al = new HashMap();

      try {
         Process p = Runtime.getRuntime().exec(new String[]{"powershell.exe", "Get-PhysicalDisk | Format-table DeviceID,MediaType"});
         p.waitFor();
         BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
         String line1 = "";
         int j = 0;

         while((line1 = in.readLine()) != null) {
            if (!line1.isEmpty()) {
               al.put(String.valueOf(j), line1.replaceAll("  +", ","));
               ++j;
            }
         }

         in.close();
      } catch (Exception var6) {
         this.logger.info("BK 103:" + var6.getMessage());
      }

      return al;
   }

   public String driveDetails() {
      String driveDetails = "<table border =1><tr><td>Drive</td><td>Total Size</td><td>Free Space</td><td>usable Space</td></tr>";
      File[] roots = File.listRoots();
      File[] var6 = roots;
      int var5 = roots.length;

      for(int var4 = 0; var4 < var5; ++var4) {
         File root = var6[var4];
         driveDetails = driveDetails + "<tr><td>" + root.getAbsolutePath().substring(0, 1) + "</td><td>" + root.getTotalSpace() / 1073741824L + " GB</td><td>" + root.getFreeSpace() / 1073741824L + " GB</td><td>" + root.getUsableSpace() / 1073741824L + " GB</td></tr>";
      }

      return driveDetails + "</table></body></html>";
   }

   public HashMap<String, String> getStorageInfo1() {
      String line = "";
      HashMap ms = new HashMap();

      try {
         Process p = Runtime.getRuntime().exec("powershell.exe wmic /namespace:\\\\root\\microsoft\\windows\\storage path msft_disk get BusType,Number,PhysicalSectorSize");
         BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
         int j = 0;

         while((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
               ms.put(String.valueOf(j), line.replaceAll("  +", ","));
               ++j;
            }
         }
      } catch (Exception var6) {
         this.logger.info("BK 105 :" + var6.getMessage());
      }

      return ms;
   }

   private String StorageType(String typeCode) {
      String retVal = "";
      switch(typeCode.hashCode()) {
      case 48:
         if (typeCode.equals("0")) {
            retVal = "Unknown";
            return retVal;
         }
         break;
      case 49:
         if (typeCode.equals("1")) {
            retVal = "SCSI";
            return retVal;
         }
         break;
      case 50:
         if (typeCode.equals("2")) {
            retVal = "ATAPI";
            return retVal;
         }
         break;
      case 51:
         if (typeCode.equals("3")) {
            retVal = "ATA";
            return retVal;
         }
         break;
      case 52:
         if (typeCode.equals("4")) {
            retVal = "IEEE 1394";
            return retVal;
         }
         break;
      case 53:
         if (typeCode.equals("5")) {
            retVal = "SSA";
            return retVal;
         }
         break;
      case 54:
         if (typeCode.equals("6")) {
            retVal = "Fibre Channel";
            return retVal;
         }
         break;
      case 55:
         if (typeCode.equals("7")) {
            retVal = "USB";
            return retVal;
         }
         break;
      case 56:
         if (typeCode.equals("8")) {
            retVal = "RAID";
            return retVal;
         }
         break;
      case 57:
         if (typeCode.equals("9")) {
            retVal = "iSCSI";
            return retVal;
         }
         break;
      case 1567:
         if (typeCode.equals("10")) {
            retVal = "Serial Attached SCSI (SAS)";
            return retVal;
         }
         break;
      case 1568:
         if (typeCode.equals("11")) {
            retVal = "SATA";
            return retVal;
         }
         break;
      case 1569:
         if (typeCode.equals("12")) {
            retVal = "Secure Digital (SD)";
            return retVal;
         }
         break;
      case 1570:
         if (typeCode.equals("13")) {
            retVal = "Multimedia Card (MMC)";
            return retVal;
         }
         break;
      case 1572:
         if (typeCode.equals("15")) {
            retVal = "File-Backed Virtual";
            return retVal;
         }
         break;
      case 1573:
         if (typeCode.equals("16")) {
            retVal = "Storage spaces";
            return retVal;
         }
         break;
      case 1574:
         if (typeCode.equals("17")) {
            retVal = "NVMe";
            return retVal;
         }
      }

      retVal = "UNKNOWN";
      return retVal;
   }

   public void run() {
      try {
         String s = this.getStorageInfo();
         WriteIntoFile f = new WriteIntoFile();
         f.writeIntoFile(s);
      } catch (Exception var3) {
         this.logger.info("BK 104" + var3.getMessage());
      }

   }

   public static void main(String[] args) {
      DriveData d = new DriveData();
      d.script();
   }
}
