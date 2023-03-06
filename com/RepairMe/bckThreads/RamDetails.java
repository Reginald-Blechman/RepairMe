package com.RepairMe.bckThreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class RamDetails implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   public String getRAMInfoNew() {
      this.logger.info("Get RAM Details");
      String line = "";
      String finaline = "";

      try {
         Process process = Runtime.getRuntime().exec("wmic memorychip get capacity, manufacturer ,SMBIOSmemorytype, speed , ConfiguredVoltage, serialNumber,PartNumber");
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

         while((line = reader.readLine()) != null) {
            if (!line.isEmpty() & !line.contains("Capacity")) {
               finaline = finaline + line + "%";
            }
         }

         int size = finaline.split("%").length;
         String[] s = new String[6 * size];

         int ij;
         for(ij = 0; ij < size; ++ij) {
            s = finaline.replaceAll("  +", "#").split("#");
         }

         process = Runtime.getRuntime().exec("wmic Memphysical get MaxCapacity, MemoryDevices");
         reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
         ij = 0;

         String sss;
         for(sss = ""; (line = reader.readLine()) != null; ++ij) {
            if (ij > 0 && !line.isEmpty()) {
               sss = line;
            }
         }

         commons c = new commons();
         finaline = "<html><body><table border =1><b><tr><td>Manufacturer</td><td>Current<br>Capacity</td><td>Total Max Capacity</td><td>Type</td><td>Frequecny <br>Conf. in BIOS</td><td>Memory Devices</td><td>Conf. Voltage</td><td>Serial No.</td><td>Part No.</td></tr></b>";
         this.logger.info("FinalLine 1" + finaline);

         for(int i = 0; i < size; ++i) {
            finaline = finaline + "<tr><td>" + s[2] + "</td><td>" + c.convertContentSize((double)Long.parseLong(s[0])) + "</td><td>" + c.convertContentSizeKBToGB(Long.parseLong(sss.split("  +")[0])) + "</td><td>" + this.ramType(s[5]) + "</td>" + "<td>" + s[6] + "</td><td>" + sss.split("  +")[1] + "</td><td>" + Long.parseLong(s[1]) + " mV</td><td>" + s[4] + "</td><td>" + s[3] + "</td></tr>";
         }

         finaline = finaline + "</table> </body></html>";
         reader.close();
      } catch (IOException var11) {
         var11.printStackTrace();
         this.logger.info("106 : " + var11.getMessage());
      }

      return finaline.replace("<br><br>", "");
   }

   private String ramType(String ramType) {
      String retVal = "";
      switch(ramType.hashCode()) {
      case 49:
         if (ramType.equals("1")) {
            retVal = "OTHER";
            return retVal;
         }
         break;
      case 50:
         if (ramType.equals("2")) {
            retVal = "DRAM";
            return retVal;
         }
         break;
      case 1598:
         if (ramType.equals("20")) {
            retVal = "DDR";
            return retVal;
         }
         break;
      case 1599:
         if (ramType.equals("21")) {
            retVal = "DDR2";
            return retVal;
         }
         break;
      case 1602:
         if (ramType.equals("24")) {
            retVal = "DDR3";
            return retVal;
         }
         break;
      case 1604:
         if (ramType.equals("26")) {
            retVal = "DDR4";
            return retVal;
         }
         break;
      case 1633:
         if (ramType.equals("34")) {
            retVal = "DDR5";
            return retVal;
         }
      }

      retVal = "UNKNOWN";
      return retVal;
   }

   public void run() {
      try {
         String s = this.getRAMInfoNew();
         this.logger.info("GetRAM Info" + s);
         String returnVal = "M::" + BackServiceEncryptData.encrypt(s) + "###";
         this.logger.info("memory :" + returnVal);
         WriteIntoFile f = new WriteIntoFile();
         f.writeIntoFile(returnVal);
      } catch (Exception var4) {
         this.logger.info("BK 107" + var4.getMessage());
      }

   }
}
