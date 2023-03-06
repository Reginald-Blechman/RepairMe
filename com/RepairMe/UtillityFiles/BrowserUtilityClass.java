package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import com.RepairMe.common.RepairMeCommon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class BrowserUtilityClass {
   Logger logger = Logger.getLogger(MyLogger.class);

   public synchronized void writeDNSDate(String s) {
      try {
         File f = new File(System.getProperty("user.dir") + "\\private\\SysFile.dll");
         if (!f.exists()) {
            f.createNewFile();
         }

         FileWriter fr = new FileWriter(f, true);
         fr.write("DT::" + s + "DN::");
         fr.close();
      } catch (Exception var4) {
         this.logger.info("3.19 : " + var4.getMessage());
      }

   }

   private String checkDNS() {
      this.logger.info("3.10 :");
      String port = "";
      String line = "";

      try {
         String[] cm = new String[]{"powershell.exe", "Get-NetIPConfiguration"};
         Process process = Runtime.getRuntime().exec(cm);
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

         while((line = reader.readLine()) != null) {
            if (line.contains("InterfaceAlias")) {
               port = line.split(":")[1].trim();
               break;
            }
         }

         reader.close();
      } catch (Exception var6) {
         this.logger.info("3.10 : " + var6.getMessage());
      }

      return port;
   }

   public String setDNS(String param) {
      this.logger.info("3.11 : " + param);
      String returnVal = "";
      Process process = null;

      try {
         String[] s;
         if (param.equals("Google")) {
            s = new String[]{"powershell.exe", "Set-DNSClientServerAddress '" + this.checkDNS() + "' -ServerAddresses('8.8.8.8','8.8.4.4')"};
            process = Runtime.getRuntime().exec(s);
         } else if (param.equals("CloudFlare")) {
            s = new String[]{"powershell.exe", "Set-DNSClientServerAddress '" + this.checkDNS() + "' -ServerAddresses('1.1.1.1','1.0.0.1')"};
            process = Runtime.getRuntime().exec(s);
         } else if (param.equals("Level 3")) {
            s = new String[]{"powershell.exe", "Set-DNSClientServerAddress '" + this.checkDNS() + "' -ServerAddresses('209.244.0.3' , '209.244.0.4')"};
            process = Runtime.getRuntime().exec(s);
         } else if (param.equals("Open DNS")) {
            s = new String[]{"powershell.exe", "Set-DNSClientServerAddress '" + this.checkDNS() + "' -ServerAddresses('208.67.222.222' , '208.67.220.220')"};
            process = Runtime.getRuntime().exec(s);
         }

         this.logger.info("DNS changed to: " + param);
         process.waitFor();
         if (!process.isAlive()) {
            returnVal = "DNS server changed successfully";
         }
      } catch (Exception var5) {
         this.logger.info("3.11 : " + var5.getMessage());
      }

      return returnVal;
   }

   public String clearDNSCache() {
      String finaline = "";
      String returnVal = "";

      try {
         String[] s = new String[]{"ipconfig /flushdns"};
         Process process = Runtime.getRuntime().exec(s);
         process.waitFor();
         if (finaline.contains("Successfully")) {
            returnVal = "DNS cache cleared sucessfully";
         } else {
            returnVal = finaline.split("##")[1];
         }

         this.logger.info("Clearing DNS cache");
      } catch (Exception var5) {
         this.logger.info("3.11 : " + var5.getMessage());
      }

      return returnVal;
   }

   public String tempFilesSizeOfBrowser() {
      String TotalTempFilesSize = "";

      try {
         int cacheFilesSize = this.cacheFilesSize("scan");
         int cookieFilesSize = this.cookieFilesSize("scan");
         int historyFileSize = this.historyFilesSize("scan");
         RepairMeCommon rc = new RepairMeCommon();
         TotalTempFilesSize = rc.convertContentSize((long)(cacheFilesSize + cookieFilesSize + historyFileSize)) + "_" + rc.convertContentSize((long)cacheFilesSize) + "_" + rc.convertContentSize((long)cookieFilesSize) + "_" + rc.convertContentSize((long)historyFileSize);
      } catch (Exception var6) {
         this.logger.info("3.1 Browser : " + var6.getMessage());
      }

      return TotalTempFilesSize;
   }

   private int cookieFilesSize(String command) {
      int a = 0;
      String edgeCacheDir = System.getProperty("user.home") + "\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default\\Network\\Cookies";
      String ChromeCacheDir = System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Network\\Cookies";
      String braveCacheFilesDir = System.getProperty("user.home") + "\\AppData\\Local\\BraveSoftware\\Brave-Browser\\User Data\\Default\\Network\\Cookies";
      String operaGxCacheDir = System.getProperty("user.home") + "\\AppData\\Roaming\\Opera Software\\Opera GX Stable\\Network\\Cookies";
      String operaCacheDir = System.getProperty("user.home") + "\\AppData\\Roaming\\Opera Software\\Opera Stable\\Network\\Cookies";
      if (command.equals("scan")) {
         a = this.dirEmptyCheckandSize(edgeCacheDir) + this.dirEmptyCheckandSize(ChromeCacheDir) + this.dirEmptyCheckandSize(braveCacheFilesDir) + this.dirEmptyCheckandSize(operaGxCacheDir) + this.dirEmptyCheckandSize(operaCacheDir);
      } else if (command.equals("purge")) {
         try {
            this.deleteFileandFolder(edgeCacheDir);
            this.deleteFileandFolder(ChromeCacheDir);
            this.deleteFileandFolder(braveCacheFilesDir);
            this.deleteFileandFolder(operaGxCacheDir);
            this.deleteFileandFolder(operaCacheDir);
            a = 1;
         } catch (Exception var9) {
            this.logger.info("3.2 : " + var9.getMessage());
         }
      }

      return a;
   }

   private int historyFilesSize(String command) {
      int a = 0;
      String edgeCacheDir = System.getProperty("user.home") + "\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default\\History";
      String ChromeCacheDir = System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\History";
      String braveCacheFilesDir = System.getProperty("user.home") + "\\AppData\\Local\\BraveSoftware\\Brave-Browser\\User Data\\Default\\History";
      String operaCacheDir = System.getProperty("user.home") + "\\AppData\\Roaming\\Opera Software\\Opera Stable\\History";
      String operaGXCacheDir = System.getProperty("user.home") + "\\AppData\\Roaming\\Opera Software\\Opera GX Stable\\History";
      if (command.equals("scan")) {
         a = this.dirEmptyCheckandSize(edgeCacheDir) + this.dirEmptyCheckandSize(ChromeCacheDir) + this.dirEmptyCheckandSize(braveCacheFilesDir) + this.dirEmptyCheckandSize(operaCacheDir) + this.dirEmptyCheckandSize(operaGXCacheDir);
      } else if (command.equals("purge")) {
         try {
            this.deleteFileandFolder(edgeCacheDir);
            this.makeHistoryFiles(edgeCacheDir);
            this.deleteFileandFolder(ChromeCacheDir);
            this.makeHistoryFiles(ChromeCacheDir);
            this.deleteFileandFolder(braveCacheFilesDir);
            this.makeHistoryFiles(braveCacheFilesDir);
            this.deleteFileandFolder(operaGXCacheDir);
            this.makeHistoryFiles(operaGXCacheDir);
            this.deleteFileandFolder(operaCacheDir);
            this.makeHistoryFiles(operaCacheDir);
            a = 1;
         } catch (Exception var9) {
            this.logger.info("3.3 : " + var9.getMessage());
         }
      }

      return a;
   }

   private void makeHistoryFiles(String path) {
      try {
         File f = new File(path);
         if (!f.exists()) {
            f.createNewFile();
         }
      } catch (Exception var3) {
         this.logger.info("3.4 : " + var3.getMessage());
      }

   }

   private int cacheFilesSize(String command) {
      int a = 0;
      String edgeCacheDir = System.getProperty("user.home") + "\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default\\Cache\\Cache_Data\\";
      String ChromeCacheDir = System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cache\\Cache_Data\\";
      String braveCacheFilesDir = System.getProperty("user.home") + "\\AppData\\Local\\BraveSoftware\\Brave-Browser\\User Data\\Default\\Cache\\Cache_Data\\";
      String operaGXCacheDir = System.getProperty("user.home") + "\\AppData\\Local\\Opera Software\\Opera GX Stable\\Cache\\Cache_Data\\";
      String operaGXSysCacheDir = System.getProperty("user.home") + "\\AppData\\Local\\Opera Software\\Opera GX Stable\\System Cache\\Cache_Data\\";
      String operaCacheFilesDir = System.getProperty("user.home") + "\\AppData\\Local\\Opera Software\\Opera Stable\\Cache\\Cache_Data\\";
      String operaSysCacheFilesDir = System.getProperty("user.home") + "\\AppData\\Local\\Opera Software\\Opera Stable\\System Cache\\Cache_Data\\";
      String edgeCodeCache = System.getProperty("user.home") + "\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default\\Code Cache\\";
      String ChromeCodeCacheDir = System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Code Cache\\";
      String braveCodedCacheFilesDir = System.getProperty("user.home") + "\\AppData\\Local\\BraveSoftware\\Brave-Browser\\User Data\\Default\\Code Cache\\";
      String operaGXCodeCacheDir = System.getProperty("user.home") + "\\AppData\\Roaming\\Opera Software\\Opera GX Stable\\Code Cache\\";
      String operaCodeCacheFilesDir = System.getProperty("user.home") + "\\AppData\\Roaming\\Opera Software\\Opera Stable\\Code Cache\\";
      if (command.equals("scan")) {
         a = this.dirEmptyCheckandSize(edgeCacheDir) + this.dirEmptyCheckandSize(ChromeCacheDir) + this.dirEmptyCheckandSize(braveCacheFilesDir) + this.dirEmptyCheckandSize(operaGXCacheDir) + this.dirEmptyCheckandSize(operaGXSysCacheDir) + this.dirEmptyCheckandSize(operaCacheFilesDir) + this.dirEmptyCheckandSize(operaSysCacheFilesDir) + this.dirEmptyCheckandSize(edgeCodeCache) + this.dirEmptyCheckandSize(ChromeCodeCacheDir) + this.dirEmptyCheckandSize(braveCodedCacheFilesDir) + this.dirEmptyCheckandSize(operaGXCodeCacheDir) + this.dirEmptyCheckandSize(operaCodeCacheFilesDir);
      } else if (command.equals("purge")) {
         try {
            this.deleteFileandFolder(edgeCacheDir);
            this.deleteFileandFolder(ChromeCacheDir);
            this.deleteFileandFolder(braveCacheFilesDir);
            this.deleteFileandFolder(operaGXCacheDir);
            this.deleteFileandFolder(operaGXSysCacheDir);
            this.deleteFileandFolder(operaCacheFilesDir);
            this.deleteFileandFolder(operaSysCacheFilesDir);
            this.deleteFileandFolder(edgeCodeCache);
            this.deleteFileandFolder(ChromeCodeCacheDir);
            this.deleteFileandFolder(braveCodedCacheFilesDir);
            this.deleteFileandFolder(operaGXCodeCacheDir);
            this.deleteFileandFolder(operaCodeCacheFilesDir);
            a = 1;
         } catch (Exception var16) {
            this.logger.info("3.6 : " + var16.getMessage());
         }
      }

      return a;
   }

   private int dirEmptyCheckandSize(String path) {
      int size = 0;
      File f = new File(path);
      if (f.exists()) {
         if (f.isDirectory()) {
            size = (int)FileUtils.sizeOfDirectory(f);
         } else {
            size = (int)f.length();
         }
      }

      return size;
   }

   private int deleteFileandFolder(String path) {
      File ff = new File(path);

      try {
         if (ff.exists()) {
            File[] var6;
            int var5 = (var6 = ff.listFiles()).length;

            for(int var4 = 0; var4 < var5; ++var4) {
               File f = var6[var4];
               if (f.length() > 0L) {
                  if (f.isDirectory()) {
                     this.deleteFileandFolder(f.getAbsolutePath());
                  } else {
                     FileUtils.forceDelete(f);
                  }
               } else {
                  FileUtils.deleteDirectory(f);
               }
            }
         }
      } catch (Exception var7) {
         this.logger.info("3.12.2:" + var7.getMessage());
      }

      return 0;
   }

   public int purgeBrowsersTemps(boolean a, boolean b, boolean c) {
      int cacheFilesSize = 0;
      int cookieFilesSize = 0;
      int historyFileSize = 0;
      if (a) {
         this.cacheFilesSize("purge");
         cacheFilesSize = 1;
      }

      if (b) {
         this.cookieFilesSize("purge");
         cookieFilesSize = 1;
      }

      if (c) {
         this.historyFilesSize("purge");
         historyFileSize = 1;
      }

      return historyFileSize + cacheFilesSize + cookieFilesSize;
   }
}
