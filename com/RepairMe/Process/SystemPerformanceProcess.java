package com.RepairMe.Process;

import com.RepairMe.UtillityFiles.SystemPerformanceUtillity;
import java.util.ArrayList;
import java.util.HashMap;

public class SystemPerformanceProcess {
   public String disbaleWidgets(String str) {
      SystemPerformanceUtillity ut = new SystemPerformanceUtillity();
      return ut.disableWidgets(str);
   }

   public String disableEnableCortana(String str) {
      SystemPerformanceUtillity ut = new SystemPerformanceUtillity();
      return ut.disableEnableCortana(str);
   }

   public String curretnRamSize() {
      SystemPerformanceUtillity ut = new SystemPerformanceUtillity();
      return ut.curretnRamSize();
   }

   public String setVirualRam(int ramvalue, int newVal) {
      SystemPerformanceUtillity ut = new SystemPerformanceUtillity();
      return ut.setVirualRam(ramvalue, newVal);
   }

   public String cortanaStatus() {
      SystemPerformanceUtillity ut = new SystemPerformanceUtillity();
      return ut.cortanaStatus();
   }

   public String runTask(String command) {
      SystemPerformanceUtillity ut = new SystemPerformanceUtillity();
      return ut.executeProcess(command);
   }

   public String startStopServices(ArrayList<String> s, String action) {
      SystemPerformanceUtillity ut = new SystemPerformanceUtillity();
      return ut.startStopSevices(s, action);
   }

   public HashMap<String, String> checkServiceStatus() {
      SystemPerformanceUtillity ut = new SystemPerformanceUtillity();
      return ut.checkServiceStatus();
   }

   public String checkWidgetStatus() {
      SystemPerformanceUtillity ut = new SystemPerformanceUtillity();
      return ut.checkWidgetStatus();
   }

   public void startStopSatrtupApps() {
      SystemPerformanceUtillity ut = new SystemPerformanceUtillity();
      ut.startStopSatrtupApps();
   }
}
