package com.RepairMe.Process;

import com.RepairMe.UtillityFiles.FirstTimeUtillity;
import com.RepairMe.UtillityFiles.PolicyThread;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FirstTimeProcess {
   public void steps(ArrayList<Boolean> options) {
   }

   public void SystemRestore() {
      FirstTimeUtillity ut = new FirstTimeUtillity();
      ut.systemRestore();
   }

   public void widnwsUpdate() {
      FirstTimeUtillity ut = new FirstTimeUtillity();
      ut.updateWindwos();
   }

   public String disabledPolicy() {
      ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);
      String[] policyDisabled = new String[]{"powershell.exe Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Personalization\\Settings' -Name 'AcceptedPrivacyPolicy' -Value 0", "powershell.exe Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Privacy' -Name 'TailoredExperiencesWithDiagnosticDataEnabled' -Value 0", "powershell.exe Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\AdvertisingInfo' -Name 'Enabled' -Value 0", "powershell.exe Set-ItemProperty -Path 'HKLM:\\Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\DataCollection' -Name 'AllowTelemetry' -Value 0"};

      for(int i = 0; i < policyDisabled.length; ++i) {
         PolicyThread ik = new PolicyThread(policyDisabled[i]);
         executor.execute(ik);
      }

      try {
         do {
            Thread.sleep(50L);
         } while(executor.getActiveCount() != 0);

         executor.shutdown();
      } catch (Exception var5) {
      }

      return "Policy setup completed succesfully";
   }

   public void uninstallApps() {
      FirstTimeUtillity ut = new FirstTimeUtillity();
      ut.uninstallApps();
   }

   public String setupVisualFX(int index) {
      FirstTimeUtillity ut = new FirstTimeUtillity();
      return ut.updateVisualFX(index);
   }

   public void listAppsToUpgrade() {
      FirstTimeUtillity ut = new FirstTimeUtillity();
      ut.appsList();
   }

   public void upgradeApps() {
      FirstTimeUtillity ut = new FirstTimeUtillity();
      ut.upgradeWinApps();
   }

   public void installApps(ArrayList<String> al, String command) {
      FirstTimeUtillity ut = new FirstTimeUtillity();
      ut.installApps(al, command);
   }
}
