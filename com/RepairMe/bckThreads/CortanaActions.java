package com.RepairMe.bckThreads;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.apache.log4j.Logger;

public class CortanaActions implements Runnable {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void run() {
      try {
         this.makeFile();
         Process process = Runtime.getRuntime().exec(new String[]{"powershell.exe", System.getProperty("user.dir") + "\\private\\disableCortana.ps1"});
         process.waitFor();
         (new File(System.getProperty("user.dir") + "\\private\\disableCortana.ps1")).delete();
      } catch (Exception var2) {
         this.logger.info("01.01" + var2.getMessage());
      }

   }

   private void makeFile() {
      try {
         BufferedWriter bf = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\private\\disableCortana.ps1"));
         bf.write("$path = \"HKLM:\\SOFTWARE\\Policies\\Microsoft\\Windows\\Windows Search\"");
         bf.newLine();
         bf.write("IF(!(Test-Path -Path $path)) {");
         bf.newLine();
         bf.write("New-Item -Path \"HKLM:\\SOFTWARE\\Policies\\Microsoft\\Windows\" -Name \"Windows Search\"");
         bf.newLine();
         bf.write("}");
         bf.newLine();
         bf.write("Set-ItemProperty -Path $path -Name \"AllowCortana\" -Value 1");
         bf.close();
      } catch (Exception var2) {
         this.logger.info("01.02" + var2.getMessage());
      }

   }

   public static void main(String[] args) {
      CortanaActions p = new CortanaActions();
      p.run();
   }
}
