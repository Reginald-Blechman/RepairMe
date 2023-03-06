package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import com.RepairMe.common.RepairMeCommon;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class UtillityClass {
   Logger logger = Logger.getLogger(MyLogger.class);

   public String purgeAllTempDir(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean h, boolean j, boolean k, boolean g) {
      String winDirPath = System.getenv("WINDIR");
      String userTempFilePath = "";
      String winTempFilePath = "";
      String winPrefetchTempFilePath = "";
      String storeCacheFiles = "";
      ExecutorService executor = Executors.newFixedThreadPool(5);
      if (a) {
         this.logger.info("user temp");
         userTempFilePath = System.getProperty("java.io.tmpdir");
         this.deletefiles(userTempFilePath);
      }

      if (b) {
         this.logger.info("Win temp");
         winTempFilePath = winDirPath + "\\Temp";
         this.deletefiles(winTempFilePath);
      }

      if (c) {
         this.logger.info("Prefetch");
         winPrefetchTempFilePath = winDirPath + "\\Prefetch";
         this.deletefiles(winPrefetchTempFilePath);
      }

      if (d) {
         this.logger.info("WinEvt");
         WinErrorThread wet = new WinErrorThread();
         executor.execute(wet);
      }

      if (e) {
         this.logger.info("WinStore");
         storeCacheFiles = System.getProperty("user.home") + "\\AppData\\Local\\Packages\\Microsoft.WindowsStore_8wekyb3d8bbwe\\AC\\INetCache";
         storeCacheFiles = System.getProperty("user.home") + "\\AppData\\Local\\Packages\\Microsoft.WindowsStore_8wekyb3d8bbwe\\AC\\INetCookies";
         this.deletefiles(storeCacheFiles);
         this.deletefiles(storeCacheFiles);
      }

      if (f) {
         this.logger.info("Soft Dist");
         DeleteSoftDistFolderThread dsdt = new DeleteSoftDistFolderThread();
         executor.execute(dsdt);
      }

      if (h) {
         this.logger.info("RBin");

         try {
            String[] s = new String[]{"powershell.exe", "Clear-RecycleBin -Force"};
            Runtime.getRuntime().exec(s);
         } catch (Exception var18) {
            var18.getMessage();
         }
      }

      if (j) {
         this.logger.info("Thumb");
         this.thumbiconSize();
      }

      if (k) {
         this.logger.info("MemDump");
         MemDumpThread md = new MemDumpThread();
         executor.execute(md);
      }

      if (g) {
         this.logger.info("DelOptim");
         DelivOptiThread t = new DelivOptiThread();
         executor.execute(t);
      }

      return "Done";
   }

   public String disableHibernate(String decide) {
      String[] disableCommand = new String[]{"powercfg.exe", "/hibernate off"};
      String[] enableCommand = new String[]{"powercfg.exe", "/hibernate on"};

      try {
         Process p;
         if (decide.equals("Enable")) {
            p = Runtime.getRuntime().exec(enableCommand);
            p.waitFor();
         } else if (decide.equals("Disable")) {
            p = Runtime.getRuntime().exec(disableCommand);
            p.waitFor();
         }
      } catch (InterruptedException | IOException var6) {
         this.logger.info("2.11 " + var6.getMessage());
      }

      return "Hibernation " + decide + "d successfully";
   }

   public void deletefiles(String path) {
      File ff = new File(path);

      try {
         if (ff.exists()) {
            File[] var6;
            int var5 = (var6 = ff.listFiles()).length;

            for(int var4 = 0; var4 < var5; ++var4) {
               File f = var6[var4];
               if (f.isDirectory()) {
                  if (f.length() > 0L) {
                     this.deletefiles(f.getAbsolutePath());
                  } else {
                     FileUtils.deleteDirectory(f);
                  }
               } else {
                  Path file = Paths.get(path + "\\" + f.getName());
                  BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                  long diff = (new Date()).getTime() - attr.lastModifiedTime().toMillis();
                  if (diff > 86400000L) {
                     FileUtils.forceDelete(f);
                  }
               }
            }
         }
      } catch (IOException var11) {
         this.logger.info("2.12.2:" + var11.getMessage());
      } catch (Exception var12) {
         this.logger.info("2.12.2:" + var12.getMessage());
      }

   }

   public String tempFilesSizeOnDisk() {
      String TotalTempFilesSize = "";
      String winDirPath = System.getenv("WINDIR");

      try {
         String userTempFilePath = System.getProperty("java.io.tmpdir");
         String winTempFilePath = winDirPath + "\\Temp";
         String winPrefetchTempFilePath = winDirPath + "\\Prefetch";
         String winErrorLogsTempFilePath = winDirPath + "\\System32\\winevt\\Logs";
         String memoryDmpFilePath = winDirPath + "\\MEMORY.DMP";
         String memoryminimdmpFilePath = winDirPath + "\\minidump\\";
         String storeCache = System.getProperty("user.home") + "\\AppData\\Local\\Packages\\Microsoft.WindowsStore_8wekyb3d8bbwe\\AC\\INetCache";
         String storeCookies = System.getProperty("user.home") + "\\AppData\\Local\\Packages\\Microsoft.WindowsStore_8wekyb3d8bbwe\\AC\\INetCookies";
         String windowUpdateTempFiles = winDirPath + "\\SoftwareDistribution";
         String thumbCache = System.getProperty("user.home") + "\\AppData\\Local\\Microsoft\\Windows\\Explorer\\";
         String deliveryOptimizationCache = System.getenv("WINDIR") + "\\ServiceProfiles\\NetworkService\\AppData\\Local\\Microsoft\\Windows\\DeliveryOptimization\\Cache";
         RepairMeCommon rc = new RepairMeCommon();
         String hibernateFile = rc.convertContentSize(this.getFolderFileSize("C:/hiberfil.sys"));
         TotalTempFilesSize = this.getFolderFileSize(winTempFilePath) + this.getFolderFileSize(userTempFilePath) + this.getFolderFileSize(winPrefetchTempFilePath) + this.getFolderFileSize(memoryDmpFilePath) + this.getFolderFileSize(memoryminimdmpFilePath) + this.getFolderFileSize(winErrorLogsTempFilePath) + this.getFolderFileSize(storeCache) + this.getFolderFileSize(storeCookies) + this.getFolderFileSize(windowUpdateTempFiles) + this.getFolderFileSize(thumbCache) + this.getFolderFileSize(deliveryOptimizationCache) + this.getFolderFileSize(hibernateFile) + "_" + rc.convertContentSize(this.getFolderFileSize(userTempFilePath)) + "_" + rc.convertContentSize(this.getFolderFileSize(winTempFilePath)) + "_" + rc.convertContentSize(this.getFolderFileSize(winPrefetchTempFilePath)) + "_" + rc.convertContentSize(this.getFolderFileSize(winErrorLogsTempFilePath)) + "_" + rc.convertContentSize(this.getFolderFileSize(memoryDmpFilePath) + this.getFolderFileSize(memoryminimdmpFilePath)) + "_" + rc.convertContentSize(this.getFolderFileSize(storeCookies) + this.getFolderFileSize(storeCache)) + "_" + rc.convertContentSize(this.getFolderFileSize(thumbCache)) + "_" + rc.convertContentSize(this.getFolderFileSize(deliveryOptimizationCache)) + "_" + rc.convertContentSize(this.getFolderFileSize(windowUpdateTempFiles)) + "_" + hibernateFile;
      } catch (Exception var16) {
         this.logger.info("2.13 : Scan : " + var16.getMessage());
      }

      return TotalTempFilesSize;
   }

   private long getFolderFileSize(String path) {
      long size = 0L;
      File f = new File(path);
      if (f.exists()) {
         if (f.isDirectory()) {
            size = FileUtils.sizeOfDirectory(f);
         } else {
            size = f.length();
         }
      }

      return size;
   }

   public void thumbiconSize() {
      String thumbCache = System.getProperty("user.home") + "\\AppData\\Local\\Microsoft\\Windows\\Explorer\\";
      File thumbCacheTempDir = new File(thumbCache);
      File[] listOfFiles = thumbCacheTempDir.listFiles();

      for(int i = 0; i < listOfFiles.length; ++i) {
         if (listOfFiles[i].getName().contains("thumbcache_*db")) {
            listOfFiles[i].delete();
         }
      }

   }
}
