package com.RepairMe.Process;

import com.RepairMe.UtillityFiles.UserSystemInfo;

public class MainUserSystemInfo {
   public String getStorgaeInfo() {
      UserSystemInfo usinfo = new UserSystemInfo();
      return usinfo.getStorageInfo();
   }

   public String getBoardInfo() {
      UserSystemInfo usinfo = new UserSystemInfo();
      return usinfo.getBoardInfo();
   }

   public String getGPUInfo() {
      UserSystemInfo usinfo = new UserSystemInfo();
      return usinfo.getGPUInfo();
   }

   public String getRAMInfoNew() {
      UserSystemInfo usinfo = new UserSystemInfo();
      return usinfo.getRAMInfoNew();
   }

   public String getOSInfo() {
      UserSystemInfo usinfo = new UserSystemInfo();
      return usinfo.getOSInfo();
   }

   public String getCPUInfo() {
      UserSystemInfo usinfo = new UserSystemInfo();
      return usinfo.getCPUInfo();
   }

   public String getRAMType(String arg) {
      String RAMType = "";
      if (arg.equals("0")) {
         RAMType = "Unknown";
      }

      if (arg.equals("1")) {
         RAMType = "OTHER";
      }

      if (arg.equals("2")) {
         RAMType = "DRAM";
      }

      if (arg.equals("20")) {
         RAMType = "DDR";
      }

      if (arg.equals("21")) {
         RAMType = "DDR2";
      }

      if (arg.equals("24")) {
         RAMType = "DDR3";
      }

      if (arg.equals("26")) {
         RAMType = "DDR4";
      }

      return RAMType;
   }
}
