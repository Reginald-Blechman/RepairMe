package com.RepairMe.Process;

import com.RepairMe.UtillityFiles.UtillityClass;
import com.RepairMe.common.RepairMeCommon;

public class MainProcessor {
   public String tempDirSizeOnDisk() {
      UtillityClass uClass = new UtillityClass();
      return uClass.tempFilesSizeOnDisk();
   }

   public String disableHibernate(String decide) {
      UtillityClass uClass = new UtillityClass();
      return uClass.disableHibernate(decide);
   }

   public String purgeUserTempDirs(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean h, boolean j, boolean k, boolean g) {
      UtillityClass uClass = new UtillityClass();
      return uClass.purgeAllTempDir(a, b, c, d, e, f, h, j, k, g);
   }

   public static void printResults() {
      try {
         String[] s = new String[]{"sfc", "/scannow"};
         Process process = Runtime.getRuntime().exec(s);
         process.waitFor();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public String converter(long l) {
      RepairMeCommon uc = new RepairMeCommon();
      return uc.convertContentSize(l);
   }
}
