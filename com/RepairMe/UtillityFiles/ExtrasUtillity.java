package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class ExtrasUtillity {
   Logger logger = Logger.getLogger(MyLogger.class);

   public String getLaptoporDesktopStatus() {
      String retVal = "";

      try {
         String[] s = new String[]{"wmic", "systemenclosure", "get", "chassistypes"};
         Process process = Runtime.getRuntime().exec(s);
         process.waitFor();
         BufferedReader bbr = new BufferedReader(new InputStreamReader(process.getInputStream()));
         String line = "";

         while((line = bbr.readLine()) != null) {
            if (line.contains("{")) {
               if (this.machineType(line).equals("OK")) {
                  retVal = this.getBatteryStatus();
               } else {
                  retVal = "No Battery Detected for Report Generation.";
               }
            }
         }

         bbr.close();
      } catch (Exception var6) {
         this.logger.info("12.1 : " + var6.getMessage());
      }

      return retVal;
   }

   private String machineType(String Type) {
      String retVal = "";
      switch(Type.hashCode()) {
      case 120033:
         if (Type.equals("{7}")) {
            retVal = "OK";
            return retVal;
         }
         break;
      case 120064:
         if (Type.equals("{8}")) {
            retVal = "OK";
            return retVal;
         }
         break;
      case 120095:
         if (Type.equals("{9}")) {
            retVal = "OK";
            return retVal;
         }
         break;
      case 3712995:
         if (Type.equals("{10}")) {
            retVal = "OK";
            return retVal;
         }
         break;
      case 3713088:
         if (Type.equals("{13}")) {
            retVal = "OK";
            return retVal;
         }
      }

      retVal = "NO";
      return retVal;
   }

   public String getBatteryStatus() {
      String line = "";
      String finaline = "";

      try {
         String[] command = new String[]{"powershell.exe", "powercfg /batteryreport /output " + System.getProperty("java.io.tmpdir") + "BReport.html"};
         Process process = Runtime.getRuntime().exec(command);
         if (process.waitFor() == 0) {
            BufferedReader bbr = new BufferedReader(new FileReader(System.getProperty("java.io.tmpdir") + "BReport.html"));

            while((line = bbr.readLine()) != null) {
               if (!line.contains("Recent usage")) {
                  finaline = finaline + line;
               } else if (line.contains("Recent usage")) {
                  break;
               }
            }

            bbr.close();
            finaline = finaline.replaceAll("<.*?>", "%");
            finaline = "<html><body><table><tr><td>" + finaline.substring(finaline.indexOf("MANUFACTURER")).replace("%%%", "</td><td>").replace("%%", "</tr><tr><td>").replace("%", "<tr><td>") + "</tr></table><br>Your Full Report is available at<br>" + System.getProperty("java.io.tmpdir") + "Battery-Report.html</h3></body></html>";
         }
      } catch (Exception var6) {
         this.logger.info("12.2 : " + var6.getMessage());
      }

      return finaline;
   }

   public String componentsStatus() {
      BufferedReader reader = null;
      String line = "";
      String finaline = "";
      String[] command = new String[]{"wmic diskdrive get caption, status", "wmic memorychip get name, status", "wmic CPU get name, status", "wmic baseboard get product, status", "wmic path win32_VideoController get name, status"};

      try {
         finaline = "<html><body><table border =1><b>";

         for(int i = 0; i < command.length; ++i) {
            Process process = Runtime.getRuntime().exec(command[i]);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            for(int j = 0; (line = reader.readLine()) != null; ++j) {
               if (j > 0 && !line.equals("")) {
                  finaline = finaline + "." + line.replaceAll("  +", "#");
               }
            }
         }

         finaline = finaline + "</b></table></body></html>";
         finaline = finaline.replace(".", "<tr><td>").replace("#", "</td><td>").replace("<td></td>", "<td>Unknown</td>");
         reader.close();
      } catch (Exception var8) {
         this.logger.info("12.3 : " + var8.getMessage());
      }

      return finaline.replaceAll("OK", "Healthy");
   }

   public String getDriveList() {
      BufferedReader reader = null;
      String line = "";
      String finaline = "";
      String[] command = new String[]{"wmic", "logicaldisk get name"};

      try {
         Process process = Runtime.getRuntime().exec(command);
         reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

         while((line = reader.readLine()) != null) {
            if (!line.equals("")) {
               finaline = finaline + line + "#";
            }
         }
      } catch (Exception var6) {
         this.logger.info("12.4 : " + var6.getMessage());
      }

      return finaline;
   }
}
