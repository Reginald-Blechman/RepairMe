package com.RepairMe.Process;

import com.RepairMe.UtillityFiles.DiskUtilitty;
import org.apache.log4j.Logger;

public class DiskProcess {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void diskCleanup(String driveLetter) {
      DiskUtilitty p = new DiskUtilitty();
      p.diskCleanup(driveLetter);
   }

   public String enableDisableIndexing(String driveLette) {
      DiskUtilitty p = new DiskUtilitty();
      return p.enableDisableIndexing(driveLette);
   }

   public String enableDisableDefrag() {
      DiskUtilitty du = new DiskUtilitty();
      return du.enableDisableDefrag();
   }

   public String chckIndexingStatusofDrive() {
      DiskUtilitty p = new DiskUtilitty();
      return p.chckIndexingStatusofDrive();
   }

   public String defragHDDisk(String driveLetter) {
      String result = "";
      DiskUtilitty p = new DiskUtilitty();
      p.defragHDDisk(driveLetter);
      return result;
   }

   public void printDriveListonFile(String as) {
      DiskUtilitty p = new DiskUtilitty();
      p.printDriveListonFile(as);
   }

   public String getDriveList() {
      DiskUtilitty p = new DiskUtilitty();
      return p.getListOfDrives();
   }

   public void optimizeSSD(String driveLetter) {
      DiskUtilitty p = new DiskUtilitty();
      p.optimizeSSD(driveLetter);
   }

   public String chkDskCommand(String driveLetter) {
      DiskUtilitty du = new DiskUtilitty();
      return du.chkDskCommand(driveLetter);
   }
}
