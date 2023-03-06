package com.RepairMe.common;

import com.RepairMe.Process.MyLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class ProcessMe {
   Logger logger = Logger.getLogger(MyLogger.class);

   public String process(String ActivationCode) {
      String retVal = "";
      String userID = "";

      try {
         File f = new File(System.getProperty("user.dir") + "\\private\\udd.dll");
         if (f.exists()) {
            String macAdd = FacilitateRepairMe.getMacAddress();
            userID = "CA" + macAdd.substring(0, 2) + macAdd.substring(4, 8) + "MA" + macAdd.substring(10);
            String UserID_1 = userID.substring(userID.length() - 3);
            String UserID_2 = userID.substring(0, 3);
            String key = UserID_1 + "NU" + UserID_2 + "MA";
            String decryptedValue = FacilitateData.decrypt(key, ActivationCode);
            if (decryptedValue.equals(userID)) {
               retVal = "S";
               this.saveSerialKey(userID, ActivationCode);
            } else {
               retVal = "N";
            }
         } else {
            retVal = "N";
         }
      } catch (Exception var10) {
         this.logger.info(var10.getMessage());
      }

      return retVal;
   }

   public void saveSerialKey(String activationID, String ActivationCode) {
      try {
         Process process = Runtime.getRuntime().exec(new String[]{"powershell.exe", "Get-Item -Path 'HKLM:\\SOFTWARE\\RepairMe'"});
         BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
         process.waitFor();
         if (br.readLine() == null) {
            String[] chckCommand = new String[]{"powershell.exe", "New-Item -Path 'HKLM:\\SOFTWARE' -Name 'RepairMe' "};
            process = Runtime.getRuntime().exec(chckCommand);
            process.waitFor();
            String[] chckCommand1 = new String[]{"powershell.exe", "New-ItemProperty -Path 'HKLM:\\SOFTWARE\\RepairMe' -Name 'ActivationCode' -value '" + ActivationCode + "'"};
            process = Runtime.getRuntime().exec(chckCommand1);
            process.waitFor();
            String[] chckCommand2 = new String[]{"powershell.exe", "New-ItemProperty -Path 'HKLM:\\SOFTWARE\\RepairMe' -Name 'ActivationID' -value '" + activationID + "'"};
            process = Runtime.getRuntime().exec(chckCommand2);
            process.waitFor();
         }

         br.close();
      } catch (Exception var8) {
         var8.printStackTrace();
         this.logger.info(var8.getMessage());
      }

   }
}
