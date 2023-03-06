package com.RepairMe.Process;

import com.RepairMe.Threads.GameModeStatusThread;
import com.RepairMe.Threads.MemoryIntigrityThread;
import com.RepairMe.Threads.PowerPlanStatus;
import com.RepairMe.Threads.VirtualmachinePlatformthread;
import com.RepairMe.UtillityFiles.EnbalePowerThrottle;
import com.RepairMe.UtillityFiles.GameUtillity;
import com.RepairMe.UtillityFiles.ServiceDisableThread;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.log4j.Logger;

public class GameProcess {
   Logger logger = Logger.getLogger(MyLogger.class);
   String listVal = "";

   public String powerPlanStatus() {
      ExecutorService executor = Executors.newFixedThreadPool(3);
      PowerPlanStatus gp = new PowerPlanStatus();
      executor.execute(gp);
      this.powerPlanList(gp);
      return this.listVal;
   }

   public void powerPlanList(PowerPlanStatus gp) {
      String ss = gp.getPowerPlanStatus();
      if (ss == null) {
         try {
            Thread.sleep(50L);
         } catch (InterruptedException var4) {
            this.logger.info(var4.getMessage());
         }

         this.powerPlanList(gp);
      } else {
         this.listVal = ss;
      }

   }

   public void enableGameMode() {
      ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);
      EnbalePowerThrottle ep = new EnbalePowerThrottle("Start");
      executor.execute(ep);
      GameModeStatusThread gmt = new GameModeStatusThread("Enable");
      executor.execute(gmt);
      ServiceDisableThread sdt = new ServiceDisableThread("Stop");
      executor.execute(sdt);

      try {
         do {
            Thread.sleep(50L);
         } while(executor.getActiveCount() != 0);

         executor.shutdown();
      } catch (Exception var6) {
         this.logger.info(var6.getMessage());
      }

   }

   public void disableGameMode() {
      ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);
      EnbalePowerThrottle ep = new EnbalePowerThrottle("Stop");
      executor.execute(ep);
      GameModeStatusThread gmt = new GameModeStatusThread("Disable");
      executor.execute(gmt);
      ServiceDisableThread sdt = new ServiceDisableThread("Start");
      executor.execute(sdt);

      try {
         do {
            Thread.sleep(50L);
         } while(executor.getActiveCount() != 0);

         executor.shutdown();
      } catch (Exception var6) {
         this.logger.info(var6.getMessage());
      }

   }

   public void setPowerPlan(String planID) {
      GameUtillity u = new GameUtillity();
      u.setPowerPlan(planID);
   }

   public void setGamePriority() {
      GameUtillity p = new GameUtillity();
      p.setGamePriority();
   }

   public void revertGamePriority() {
      GameUtillity p = new GameUtillity();
      p.revertGamePriority();
   }

   public String disableMemoryInteigrity(String status) {
      GameUtillity ui = new GameUtillity();
      ui.disableMemoryInteigrity(status);
      if (status.equals("1")) {
         return "Disable";
      } else {
         return status.equals("0") ? "Enable" : "Disable";
      }
   }

   public String vmpMode(String status) {
      GameUtillity u = new GameUtillity();
      u.vmpMode(status);
      if (status.equals("Enable")) {
         return "Disable";
      } else {
         return status.equals("Disable") ? "Enable" : "Disable";
      }
   }

   public String testCallable() {
      String retVal = "";
      ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);
      final PowerPlanStatus gp = new PowerPlanStatus();
      executor.execute(gp);
      final VirtualmachinePlatformthread vm = new VirtualmachinePlatformthread();
      executor.execute(vm);
      final MemoryIntigrityThread mt = new MemoryIntigrityThread();
      executor.execute(mt);
      Set<Callable<String>> callable = new HashSet();
      callable.add(new Callable<String>() {
         public String call() throws Exception {
            return gp.getPowerPlanStatus();
         }
      });
      callable.add(new Callable<String>() {
         public String call() throws Exception {
            return vm.getVmStatus();
         }
      });
      callable.add(new Callable<String>() {
         public String call() throws Exception {
            return mt.getCoreIsolationStatus();
         }
      });

      try {
         while(executor.getActiveCount() != 0) {
            Thread.sleep(50L);
         }

         List<Future<String>> futures = executor.invokeAll(callable);
         boolean allDone = true;
         Iterator var10 = futures.iterator();

         while(var10.hasNext()) {
            Future<String> future = (Future)var10.next();
            allDone &= future.isDone();
            if (allDone) {
               retVal = retVal + (String)future.get() + "%";
            }
         }

         executor.shutdown();
      } catch (Exception var11) {
         this.logger.info(var11.getMessage());
      }

      return retVal;
   }
}
