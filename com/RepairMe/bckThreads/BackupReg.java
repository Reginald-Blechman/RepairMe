package com.RepairMe.bckThreads;

import java.io.File;
import org.apache.log4j.Logger;

public class BackupReg implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void run() {
      this.backupRegistry();
   }

   private void backupRegistry() {
      try {
         File ff;
         Process process;
         Process process1;
         if ((new File(System.getProperty("user.dir") + "\\backup\\OldBackup\\")).listFiles().length == 2) {
            ff = new File(System.getProperty("user.dir") + "\\backup\\");
            process = Runtime.getRuntime().exec("powershell.exe reg export HKCU " + ff.getAbsolutePath() + "//HKCU.Reg /y");
            if (!process.isAlive()) {
               this.logger.info("Taking Registry backup for HKCU on desktop");
            }

            process1 = Runtime.getRuntime().exec("powershell.exe reg export HKLM " + ff.getAbsolutePath() + "//HKLM.Reg /y");
            if (!process1.isAlive()) {
               this.logger.info("Taking Registry backup for HKLM on desktop");
            }
         } else {
            ff = new File(System.getProperty("user.dir") + "\\backup\\OldBackup\\");
            process = Runtime.getRuntime().exec("powershell.exe reg export HKCU " + ff.getAbsolutePath() + "//HKCU.Reg /y");
            if (!process.isAlive()) {
               this.logger.info("Taking Registry backup for HKCU on desktop");
            }

            process1 = Runtime.getRuntime().exec("powershell.exe reg export HKLM " + ff.getAbsolutePath() + "//HKLM.Reg /y");
            if (!process1.isAlive()) {
               this.logger.info("Taking Registry backup for HKLM on desktop");
            }
         }
      } catch (Exception var4) {
         this.logger.info("14.4" + var4.getMessage());
      }

   }
}
