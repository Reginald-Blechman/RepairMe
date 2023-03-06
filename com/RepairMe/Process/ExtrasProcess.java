package com.RepairMe.Process;

import com.RepairMe.UtillityFiles.ExtrasUtillity;

public class ExtrasProcess {
   public String checkLaptoporDesktop() {
      ExtrasUtillity bt = new ExtrasUtillity();
      return bt.getLaptoporDesktopStatus();
   }

   public String batteryStatus() {
      String status = "";
      ExtrasUtillity bt = new ExtrasUtillity();
      status = bt.getBatteryStatus();
      return status;
   }

   public String componentsStatus() {
      String status = "";
      ExtrasUtillity bt = new ExtrasUtillity();
      status = bt.componentsStatus();
      return status;
   }
}
