package com.RepairMe.bckThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;

public class BackServiceEncryptData {
   static Logger logger = Logger.getLogger(MyLogger.class);
   private static final String SALTVALUE = "Ganesh998ManuOne";

   public static String getMacAddress1() {
      String macAdd = "";

      try {
         File f = new File(System.getProperty("user.dir") + "\\private\\udd.dll");
         BufferedReader reader;
         if (!f.exists()) {
            Process process = Runtime.getRuntime().exec("powershell.exe getmac");
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            int j = 0;

            while((line = reader.readLine()) != null) {
               if (!line.isEmpty()) {
                  if (j > 1) {
                     macAdd = line.split(" +")[0].replace("-", "").toUpperCase();
                     break;
                  }

                  ++j;
               }
            }
         } else {
            reader = new BufferedReader(new FileReader(f));
            String fileVal = reader.readLine();
            Process process;
            String line;
            int j;
            if (fileVal.isEmpty()) {
               process = Runtime.getRuntime().exec("powershell.exe getmac");
               reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
               line = "";
               j = 0;

               while((line = reader.readLine()) != null) {
                  if (!line.isEmpty()) {
                     if (j > 1) {
                        macAdd = line.split(" +")[0].replace("-", "").toUpperCase();
                        break;
                     }

                     ++j;
                  }
               }
            } else {
               process = Runtime.getRuntime().exec("powershell.exe getmac");
               reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
               line = "";
               j = 0;
               boolean a = false;

               while((line = reader.readLine()) != null) {
                  if (!line.isEmpty()) {
                     if (j > 1) {
                        macAdd = line.split(" +")[0].replace("-", "").toUpperCase();
                        String userID = "CA" + macAdd.substring(0, 2) + macAdd.substring(4, 8) + "MA" + macAdd.substring(10);
                        if (userID.equals(fileVal)) {
                           a = true;
                        }
                     }

                     ++j;
                  }
               }

               if (!a) {
                  logger.info("MAC 007 : User file not found");
                  System.exit(0);
               }
            }
         }

         reader.close();
      } catch (Exception var9) {
         var9.printStackTrace();
      }

      return macAdd.toUpperCase();
   }

   public static String getMacAddress() {
      String macAdd = getMacAddress1();

      try {
         File f = new File(System.getProperty("user.dir") + "\\private\\udd.dll");
         String userID = "CA" + macAdd.substring(0, 2) + macAdd.substring(4, 8) + "MA" + macAdd.substring(10);
         if (!f.exists()) {
            f.createNewFile();
         }

         FileWriter ff = new FileWriter(f);
         ff.write(userID.toUpperCase());
         ff.close();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return macAdd.toUpperCase();
   }

   public static String encrypt(String strToEncrypt) {
      try {
         byte[] iv = new byte[16];
         IvParameterSpec ivspec = new IvParameterSpec(iv);
         SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
         KeySpec spec = new PBEKeySpec(getMacAddress().toCharArray(), "Ganesh998ManuOne".getBytes(), 65536, 256);
         SecretKey tmp = factory.generateSecret(spec);
         SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
         Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
         cipher.init(1, secretKey, ivspec);
         return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
      } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | InvalidAlgorithmParameterException var8) {
         return null;
      }
   }
}
