package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import com.RepairMe.Threads.CheckServiceStatusThread;
import com.RepairMe.common.RepairMeCommon;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class SystemPerformanceUtillity {
   Logger logger = Logger.getLogger(MyLogger.class);

   private String processCmd(String[] s) {
      String finaLine = "";

      try {
         Process process = Runtime.getRuntime().exec(s);
         process.waitFor();
      } catch (Exception var4) {
         this.logger.info("4.1 : " + var4.getMessage());
      }

      return finaLine.substring(finaLine.indexOf("=") + 1, finaLine.length());
   }

   private void createFile(int ramSize, int newVal) {
      StringBuffer br = new StringBuffer();
      br.append("$computersys = Get-WmiObject Win32_ComputerSystem -EnableAllPrivileges \n");
      br.append("$computersys.AutomaticManagedPagefile = $False \n");
      br.append("$computersys.Put() \n");
      br.append("$pagefile = Get-WmiObject -Query \"Select * From Win32_PageFileSetting Where Name like '%pagefile.sys'\" \n");
      br.append("$pagefile.InitialSize = " + ramSize * 1024 + " \n");
      br.append("$pagefile.MaximumSize = " + newVal * 1024 + " \n");
      br.append("$pagefile.Put()");

      try {
         FileWriter fw = new FileWriter(new File(System.getProperty("user.dir") + "\\private\\ramSetup.ps1"));
         fw.write(br.toString());
         fw.close();
      } catch (IOException var5) {
         var5.printStackTrace();
         this.logger.info("04.001" + var5.getMessage());
      }

   }

   public String setVirualRam(int ramSize, int newVal) {
      try {
         this.createFile(ramSize, newVal);
         Process process = Runtime.getRuntime().exec(new String[]{"powershell.exe", System.getProperty("user.dir") + "\\private\\ramSetup.ps1"});
         process.waitFor();
         (new File(System.getProperty("user.dir") + "\\private\\ramSetup.ps1")).delete();
      } catch (Exception var4) {
         var4.printStackTrace();
         this.logger.info("04.02" + var4.getMessage());
      }

      return "Virtual memory Set to " + newVal + " You need to restart your PC to take this effect";
   }

   public String curretnRamSize() {
      long l = 0L;

      try {
         Process process = Runtime.getRuntime().exec(new String[]{"powershell.exe", "wmic memorychip get capacity"});
         process.waitFor();
         BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
         String finaLine = "";

         for(int j = 0; (finaLine = in.readLine()) != null; ++j) {
            if (j > 0 && !finaLine.isEmpty()) {
               l += Long.parseLong(finaLine.trim());
            }
         }
      } catch (Exception var7) {
         this.logger.info("04.02" + var7.getMessage());
      }

      RepairMeCommon c = new RepairMeCommon();
      return c.convertContentSize(l);
   }

   public String cortanaStatus() {
      String retval = "";

      try {
         Process process = Runtime.getRuntime().exec(new String[]{"powershell.exe", "Get-ItemProperty -Path 'HKLM:\\SOFTWARE\\Policies\\Microsoft\\Windows\\Windows Search' | Get-Member -Name 'AllowCortana'"});
         process.waitFor();
         BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
         String finaLine = "";
         String s = "";

         while((finaLine = in.readLine()) != null) {
            if (finaLine.contains("=")) {
               s = finaLine.substring(finaLine.indexOf("=") + 1, finaLine.length());
            }
         }

         if (s.equals("0")) {
            retval = "Enable";
         } else if (s.equals("1")) {
            retval = "Disable";
         }
      } catch (Exception var6) {
         this.logger.info("04.02" + var6.getMessage());
      }

      return retval;
   }

   public String disableEnableCortana(String str) {
      String retval = "";
      String val = "0";

      try {
         if ("Enable".equals(str)) {
            val = "1";
            retval = "Successfully " + str + "ed";
         } else {
            val = "0";
            retval = "Successfully " + str + "ed";
         }

         this.makeFile(val);
         Process process = Runtime.getRuntime().exec(new String[]{"powershell.exe", System.getProperty("user.dir") + "\\private\\DCortana.ps1"});
         process.waitFor();
         (new File(System.getProperty("user.dir") + "\\private\\DCortana.ps1")).delete();
      } catch (Exception var5) {
         this.logger.info("04.02" + var5.getMessage());
      }

      return retval;
   }

   private void makeFile(String val) {
      try {
         BufferedWriter bf = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\private\\DCortana.ps1"));
         bf.write("Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Policies\\Microsoft\\Windows\\Windows Search' -Name 'AllowCortana' -value " + val);
         bf.newLine();
         bf.write("Stop-Process -name explorer");
         bf.close();
      } catch (Exception var3) {
         this.logger.info("01.02" + var3.getMessage());
      }

   }

   public String checkWidgetStatus() {
      String retVal = "";

      try {
         String[] chckCommand = new String[]{"powershell.exe", " Get-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Advanced' | Get-Member -Name 'TaskbarDa'"};
         Process process = Runtime.getRuntime().exec(chckCommand);
         process.waitFor();
         BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
         String finaLine = "";
         String s1 = "";

         while((s1 = in.readLine()) != null) {
            if (s1.contains("TaskbarDa=")) {
               finaLine = s1;
            }
         }

         String s = finaLine.substring(finaLine.indexOf("=") + 1, finaLine.length());
         if (s.equals("0")) {
            retVal = "Enable";
         } else if (s.equals("1")) {
            retVal = "Disable";
         }
      } catch (Exception var8) {
         this.logger.info("4.8 : " + var8.getMessage());
      }

      return retVal;
   }

   public String disableWidgets(String str) {
      String result = "";

      try {
         String[] disablecommand = new String[]{"powershell.exe", " Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Advanced' -Name 'TaskbarDa' -Value 0"};
         String[] enableCommand = new String[]{"powershell.exe", " Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Advanced' -Name 'TaskbarDa' -Value 1"};
         if (str.equals("Enable")) {
            this.processCmd(disablecommand);
            result = "Widget Enabled Successfully";
            this.logger.info("Enabling Widget");
         } else if (str.equals("Disable")) {
            this.processCmd(enableCommand);
            result = "Widget Disabled Successfully";
            this.logger.info("Disabling Widget");
         }
      } catch (Exception var5) {
         this.logger.info("4.2 : " + var5.getMessage());
      }

      return result;
   }

   public String executeProcess(String command) {
      Process process = null;
      String line = "";

      try {
         String[] result;
         if (command.equals("sfc")) {
            result = new String[]{"cmd", "/c", "start", "cmd.exe", "/K", "sfc /scannow"};
            process = Runtime.getRuntime().exec(result);
         } else if (command.equals("dism")) {
            result = new String[]{"cmd", "/c", "start", "cmd.exe", "/K", "DISM /Online /Cleanup-Image /RestoreHealth"};
            process = Runtime.getRuntime().exec(result);
         }

         process.waitFor();
         if (command.equals("sfc")) {
            line = "Sucessess : Log Present in C:\\Windows\\Logs\\CBS\\CBS.log";
            this.logger.info("Cheking system for Intigrity Issues.");
         } else if (command.equals("dism")) {
            line = "Sucessess : Log Present in C:\\Windows\\Logs\\DISM\\dism.log";
            this.logger.info("Cheking system for Windows Issues.");
         }
      } catch (Exception var5) {
         this.logger.info("4.4 : " + var5.getMessage());
      }

      return line;
   }

   public HashMap<String, String> checkServiceStatus() {
      ExecutorService executor = Executors.newFixedThreadPool(3);
      String[] list = new String[]{"SysMain", "WSearch", "icssvc", "fax", "TermService", "RemoteRegistry", "RetailDemo", "TapiSrv", "SCardSvr", "DiagTrack", "MapsBroker", "CertPropSvc", "TabletInputService", "wisvc", "AJRouter"};

      for(int i = 0; i < list.length; ++i) {
         CheckServiceStatusThread wet = new CheckServiceStatusThread(list[i]);
         executor.execute(wet);
      }

      new HashMap();
      File f = new File(System.getProperty("java.io.tmpdir") + "\\ServiceStatus.txt");
      HashMap<String, String> kl = this.processFileStatus(f, list);
      executor.shutdown();
      return kl;
   }

   private HashMap<String, String> processFileStatus(File f, String[] list) {
      HashMap kl = new HashMap();

      try {
         if (f.exists() && f.length() > 0L) {
            String s = FileUtils.readFileToString(f);
            String[] arr = s.split("@");

            for(int i = 0; i < list.length - 1; ++i) {
               kl.put(arr[i].split("#")[0], arr[i].split("#")[1]);
            }

            FileUtils.forceDelete(f);
            return kl;
         }

         Thread.sleep(50L);
         this.processFileStatus(f, list);
      } catch (InterruptedException | IOException var7) {
         this.logger.info("4.10 : " + var7.getMessage());
      }

      return kl;
   }

   public String startStopSevices(ArrayList<String> s, String action) {
      String finalResult = "";
      Process p = null;
      this.logger.info(action + "Selected Services : " + s);

      try {
         boolean result = true;
         String servicesList = "";

         for(int i = 0; i < s.size(); ++i) {
            servicesList = servicesList + "\"" + (String)s.get(i) + "\"" + ",";
         }

         String[] stopService = new String[]{"powershell.exe", "Stop-Service -force -name " + servicesList.substring(0, servicesList.length() - 1) + " -verbose"};
         String[] startService = new String[]{"powershell.exe", "Start-Service -name " + servicesList.substring(0, servicesList.length() - 1) + " -verbose"};
         if (action.equals("stop")) {
            result = true;
            p = Runtime.getRuntime().exec(stopService);
            p.waitFor();
            if (!p.isAlive()) {
               result = p.exitValue() == 0;
            }

            this.logger.info("Disabling services : " + servicesList.substring(0, servicesList.length() - 1));
         } else if (action.equals("start")) {
            result = true;
            p = Runtime.getRuntime().exec(startService);
            p.waitFor();
            if (!p.isAlive()) {
               result = p.exitValue() == 0;
            }

            this.logger.info("Enabling services : " + servicesList.substring(0, servicesList.length() - 1));
         }

         if (result) {
            finalResult = "sucess";
         } else {
            finalResult = "fail";
         }

         Thread.sleep(1000L);
      } catch (Exception var9) {
         this.logger.info("4.6 : " + var9.getMessage());
      }

      return finalResult;
   }

   public void startStopSatrtupApps() {
      try {
         String[] s = new String[]{"powershell.exe", "Start ms-settings:startupapps"};
         Process p = Runtime.getRuntime().exec(s);
         p.waitFor();
         this.logger.info("Trying to Disable startup Apps");
      } catch (Exception var3) {
         this.logger.info("4.7 : " + var3.getMessage());
      }

   }
}
