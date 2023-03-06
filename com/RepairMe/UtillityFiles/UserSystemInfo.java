package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import com.RepairMe.common.FacilitateRepairMe;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class UserSystemInfo {
   Logger logger = Logger.getLogger(MyLogger.class);

   public String getStorageInfo() {
      String finaline = "";

      try {
         String path = System.getProperty("user.dir") + "\\private\\SysFile.dll";
         FileReader reader = new FileReader(path);
         BufferedReader bufferedReader = new BufferedReader(reader);
         String line = "";

         while((line = bufferedReader.readLine()) != null) {
            if (!line.isEmpty()) {
               finaline = finaline + line.substring(line.indexOf("S::") + 3);
            }
         }

         reader.close();
         finaline = finaline.substring(0, finaline.indexOf("---"));
      } catch (Exception var6) {
         this.logger.info("1.1 : " + var6.getMessage());
      }

      return FacilitateRepairMe.decrypt(finaline);
   }

   public String getRAMInfoNew() {
      String finaline = "";

      try {
         FileReader reader = new FileReader(System.getProperty("user.dir") + "\\private\\SysFile.dll");
         BufferedReader bufferedReader = new BufferedReader(reader);
         String line = "";

         while((line = bufferedReader.readLine()) != null) {
            if (!line.isEmpty()) {
               finaline = finaline + line;
            }
         }

         reader.close();
      } catch (IOException var5) {
         this.logger.info("1.2 : " + var5.getMessage());
      }

      finaline = finaline.replace("<br><br>", "");
      finaline = finaline.substring(finaline.indexOf("M::") + 3, finaline.indexOf("###"));
      return FacilitateRepairMe.decrypt(finaline);
   }

   private String convertContentSize(long size_bytes) {
      float size_kb = (float)Math.round((float)(size_bytes / 1024L));
      int size_mb = Math.round(size_kb / 1024.0F);
      int size_gb = size_mb / 1024;
      String cnt_size;
      if (size_gb > 0) {
         cnt_size = size_gb + " GB";
      } else if (size_mb > 0) {
         cnt_size = size_mb + " MB";
      } else {
         cnt_size = size_kb + " KB";
      }

      return cnt_size;
   }

   private String convertContentSizeKBToGB(long size_bytes) {
      String cnt_size = "";
      float size_mb = (float)(size_bytes / 1024L);
      float size_gb = (float)Math.round(size_mb / 1024.0F);
      if (size_gb > 0.0F) {
         cnt_size = size_gb + " GB";
      } else if (size_mb > 0.0F) {
         cnt_size = size_mb + " MB";
      }

      return cnt_size;
   }

   public String getOSInfo() {
      String line = "";
      String finaline = "";

      try {
         Process process = Runtime.getRuntime().exec("wmic os get Caption,csName,FreePhysicalMemory,TotalVirtualMemorySize,TotalVisibleMemorySize,Version,SerialNumber,RegisteredUser,SizeStoredInPagingFiles,OSArchitecture");
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
         int j = 0;

         String[] s;
         for(s = new String[9]; (line = reader.readLine()) != null; ++j) {
            if (j > 0 && !line.isEmpty()) {
               line = line.replaceAll("  +", "#");
               break;
            }
         }

         s = line.split("#");
         finaline = "<html><body><table border=1><tr colspan=5><td>OS Name</td><td>" + s[0] + "</td></tr>" + "<tr colspan=5><td>OS Version</td><td>" + s[9] + "</td></tr>" + "<tr colspan=5><td>Registered User</td><td>" + s[4] + "</td></tr>" + "<tr colspan=5><td>Serial Number</td><td>" + s[5] + "</td></tr>" + "<tr colspan=5><td>Physical Memory</td><td>" + this.convertContentSizeKBToGB(Long.parseLong(s[2])) + "</td></tr>" + "<tr colspan=5><td>Virtual Memory</td><td>" + this.convertContentSizeKBToGB(Long.parseLong(s[7])) + "</td></tr>" + "<tr colspan=5><td>Total Memory</td><td>" + this.convertContentSizeKBToGB(Long.parseLong(s[8])) + "</td></tr>" + "<tr colspan=5><td>Paging Size</td><td>" + this.convertContentSizeKBToGB(Long.parseLong(s[6])) + "</td></tr>" + "<tr colspan=5><td>Device Name</td><td>" + s[1] + "</td></tr></table></body></html>";
         reader.close();
      } catch (Exception var7) {
         this.logger.info("1.3 : " + var7.getMessage());
      }

      return finaline;
   }

   public String getCPUInfo() {
      String line = "";
      String finaline = "";

      try {
         Process process = Runtime.getRuntime().exec("wmic cpu get CurrentClockSpeed,CurrentVoltage,Name,L2CacheSize,L3CacheSize,Manufacturer,MaxClockSpeed,NumberOfCores,NumberOfEnabledCore,ThreadCount,PartNumber,SerialNumber");
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
         int j = 0;

         String[] s;
         for(s = new String[12]; (line = reader.readLine()) != null; ++j) {
            if (j > 0 && !line.isEmpty()) {
               line = line.replaceAll("  +", "#");
               break;
            }
         }

         s = line.split("#");
         finaline = "<html><body><table border ='1'><tr colspan=5><td>CPU Name</td><td>" + s[6] + "</td></tr>" + "<tr colspan=5><td>Manufacturer</td><td>" + s[4] + "</td></tr>" + "<tr colspan=5><td>Total Cores</td><td>" + s[7] + "</td></tr>" + "<tr colspan=5><td>Enabled Cores</td><td>" + s[8] + "</td></tr>" + "<tr colspan=5><td>Total Threads</td><td>" + s[11] + "</td></tr>" + "<tr colspan=5><td>Max ClockSpeed</td><td>" + s[5] + "</td></tr>" + "<tr colspan=5><td>Current ClockSpeed</td><td>" + s[0] + "</td></tr>" + "<tr colspan=5><td>Current Voltage</td><td>" + s[1] + "</td></tr>" + "<tr colspan=5><td>L2 Cache Size</td><td>" + this.convertContentSizeKBToGB(Long.parseLong(s[2])) + "</td></tr>" + "<tr colspan=5><td>L3 Cache Size</td><td>" + this.convertContentSizeKBToGB(Long.parseLong(s[3])) + "</td></tr>" + "<tr colspan=5><td>Part Number</td><td>" + s[9] + "</td></tr>" + "<tr colspan=5><td>Serial Number</td><td>" + s[10] + "</td></tr>";
         reader.close();
      } catch (Exception var7) {
         this.logger.info("1.4 : " + var7.getMessage());
         var7.printStackTrace();
      }

      return finaline;
   }

   public String getGPUInfo() {
      String line = "";
      String finaline = "";

      try {
         Process process = Runtime.getRuntime().exec("wmic path win32_VideoController get DriverVersion,name,adapterram,CurrentRefreshRate,MaxRefreshRate,MinRefreshRate,AdapterCompatibility");
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

         for(int j = 0; (line = reader.readLine()) != null; ++j) {
            if (j > 0 && !line.isEmpty()) {
               line = line.replaceAll("  +", "#");
               break;
            }
         }

         String[] s = new String[7];
         s = line.split("#");
         finaline = "<html><body><table border ='1'><tr colspan=5><td>GPU Name</td><td>" + s[6] + "</td></tr>" + "<tr colspan=5><td>Manufacturer</td><td>" + s[0] + "</td></tr>" + "<tr colspan=5><td>Driver Version</td><td>" + s[3] + "</td></tr>" + "<tr colspan=5><td>VRAM</td><td>" + this.convertContentSize(Long.parseLong(s[1])) + "</td></tr>" + "<tr colspan=5><td>Max Refresh Rate</td><td>" + s[4] + " Hz</td></tr>" + "<tr colspan=5><td>Min Refresh Rate</td><td>" + s[5] + " Hz</td></tr>" + "<tr colspan=5><td>Current Refresh Rate</td><td>" + s[2] + " Hz</td></tr>";
         reader.close();
      } catch (Exception var7) {
         this.logger.info("1.5 : " + var7.getMessage());
      }

      return finaline;
   }

   public String getBoardInfo() {
      String line = "";
      String line1 = "";
      String finaline = "";

      try {
         Process process = Runtime.getRuntime().exec("wmic baseboard get product,manufacturer,SerialNumber,HotSwappable");
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

         for(int j = 0; (line = reader.readLine()) != null; ++j) {
            if (j > 0) {
               line1 = line1 + line.replaceAll("  +", "#");
            }
         }

         String[] s = new String[4];
         s = line1.split("#");
         finaline = "<html><body><table border ='1'><tr><td>Board Name</td><td>Manufacturer</td><td>Hot Swappable</td><td>Serial No</td></tr><tr><td>" + s[2] + "</td><td>" + s[1] + "</td><td>" + s[0] + "</td><td>" + s[3] + "</td></tr>" + "</table>";
         reader.close();
         this.makeFile();
         ProcessBuilder pb = new ProcessBuilder(new String[]{"powershell.exe", System.getProperty("java.io.tmpdir") + "\\prot.ps1"});
         Process p = pb.start();
         reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
         line = "";
         line1 = "<br><br><table border ='1'><tr><td>Sr No.</td><td>Ports</td></tr>";
         int jj = 0;

         while((line = reader.readLine()) != null) {
            if (!line.isEmpty() && line.contains("<td>")) {
               line1 = line1 + "<tr><td>" + jj + "</td>" + line;
               ++jj;
            }
         }

         finaline = finaline + line1 + "</table></body></html>";
      } catch (Exception var11) {
         this.logger.info("1.6 : " + var11.getMessage());
      }

      (new File(System.getProperty("java.io.tmpdir") + "\\prot.ps1")).delete();
      return finaline;
   }

   public void makeFile() {
      try {
         BufferedWriter br = new BufferedWriter(new FileWriter(System.getProperty("java.io.tmpdir") + "\\prot.ps1"));
         br.append("$portVals = Get-CimInstance -ClassName Win32_PortConnector \n");
         br.append("foreach ($port in $portVals) { \n");
         br.append("$extarnal = $port.externalreferencedesignator \n");
         br.append("Write-Output \"<td>$extarnal</td></tr>\" \n");
         br.append("}");
         br.close();
      } catch (Exception var2) {
         this.logger.info("01.02" + var2.getMessage());
      }

   }
}
