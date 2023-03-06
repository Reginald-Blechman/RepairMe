package com.RepairMe.bckProcess;

import com.RepairMe.Process.MyLogger;
import com.RepairMe.bckThreads.BackServiceEncryptData;
import com.RepairMe.bckThreads.BackupReg;
import com.RepairMe.bckThreads.CheckSysStatus;
import com.RepairMe.bckThreads.CortanaActions;
import com.RepairMe.bckThreads.DriveData;
import com.RepairMe.bckThreads.DriverListThread;
import com.RepairMe.bckThreads.InstallWinGet;
import com.RepairMe.bckThreads.RamDetails;
import com.RepairMe.bckThreads.RegistryWorkers;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.log4j.Logger;

public class RunMe {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void runAll() {
      try {
         ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
         InstallWinGet winget = new InstallWinGet();
         executor.execute(winget);
         DriverListThread dl = new DriverListThread();
         executor.execute(dl);
         DriveData data = new DriveData();
         executor.execute(data);
         RamDetails rd = new RamDetails();
         executor.execute(rd);
         CheckSysStatus st = new CheckSysStatus();
         executor.execute(st);
         BackupReg reg = new BackupReg();
         executor.execute(reg);
         CortanaActions ca = new CortanaActions();
         executor.execute(ca);
         RegistryWorkers worker = new RegistryWorkers();
         executor.execute(worker);
         BackServiceEncryptData.getMacAddress();

         do {
            Thread.sleep(50L);
         } while(executor.getActiveCount() != 0);

         executor.shutdown();
      } catch (InterruptedException var10) {
         this.logger.info(var10.toString());
      }

   }
}
