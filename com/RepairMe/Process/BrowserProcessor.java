package com.RepairMe.Process;

import com.RepairMe.UtillityFiles.BrowserUtilityClass;

public class BrowserProcessor {
   public String tempDirSizeOnDisk() {
      BrowserUtilityClass uClass = new BrowserUtilityClass();
      return uClass.tempFilesSizeOfBrowser();
   }

   public String clearDNS() {
      BrowserUtilityClass u = new BrowserUtilityClass();
      return u.clearDNSCache();
   }

   public void writeDateDNS(String s) {
      BrowserUtilityClass u = new BrowserUtilityClass();
      u.writeDNSDate(s);
   }

   public int purgeBrowserData(boolean a, boolean b, boolean c) {
      BrowserUtilityClass u = new BrowserUtilityClass();
      return u.purgeBrowsersTemps(a, b, c);
   }

   public String setDNS(String selection) {
      BrowserUtilityClass u = new BrowserUtilityClass();
      return u.setDNS(selection);
   }
}
