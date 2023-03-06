package com.RepairMe.common;

import com.RepairMe.Process.MyLogger;
import java.io.File;
import org.apache.log4j.Logger;

public class SystemDriveSize {
   Logger logger = Logger.getLogger(MyLogger.class);

   public String osDriveSize(String drive) {
      File aDrive = new File(drive);
      RepairMeCommon me = new RepairMeCommon();
      long l = aDrive.getFreeSpace();
      long l1 = aDrive.getTotalSpace();
      double f = (double)l1 / (double)l;
      int size = (int)(100.0D - 100.0D / f);
      System.out.println(size);
      int imgName = this.drivePercent(size);
      String ss = aDrive.getAbsolutePath().split(":")[0] + ":/ Drive" + "#" + me.convertContentSize(aDrive.getTotalSpace()) + "#" + me.convertContentSize(aDrive.getFreeSpace()) + "#" + size + "#" + imgName;
      this.logger.info("OSDrive : 10.1 : " + aDrive.getAbsolutePath().split(":")[0]);
      return ss;
   }

   private int drivePercent(int size) {
      int retVal = 0;
      if (size == 0) {
         retVal = 0;
      } else if (size > 0 && size < 5) {
         retVal = 5;
      } else if (5 < size && size < 13) {
         retVal = 10;
      } else if (13 <= size && size < 20) {
         retVal = 20;
      } else if (20 <= size && size < 30) {
         retVal = 30;
      } else if (30 <= size && size < 40) {
         retVal = 40;
      } else if (40 <= size && size < 47) {
         retVal = 45;
      } else if (47 <= size && size < 52) {
         retVal = 50;
      } else if (52 <= size && size < 57) {
         retVal = 55;
      } else if (57 <= size && size < 63) {
         retVal = 60;
      } else if (63 <= size && size < 67) {
         retVal = 65;
      } else if (67 <= size && size < 73) {
         retVal = 70;
      } else if (73 <= size && size < 77) {
         retVal = 75;
      } else if (77 <= size && size < 83) {
         retVal = 80;
      } else if (83 <= size && size < 90) {
         retVal = 90;
      } else if (90 <= size && size < 95) {
         retVal = 95;
      } else if (95 <= size && size < 98) {
         retVal = 98;
      } else if (98 <= size && size <= 100) {
         retVal = 100;
      }

      return retVal;
   }
}
