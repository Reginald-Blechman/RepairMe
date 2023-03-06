package com.RepairMe.common;

import com.RepairMe.Process.MyLogger;
import com.RepairMe.bckThreads.BackServiceEncryptData;
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

public class FacilitateRepairMe {
   static Logger logger = Logger.getLogger(MyLogger.class);
   private static final String SALTVALUE = "Ganesh998ManuOne";

   public static String getMacAddress() {
      String macAddress = "";
      macAddress = BackServiceEncryptData.getMacAddress1();
      return macAddress.toUpperCase();
   }

   public static String decrypt(String key, String strDecrypt) {
      try {
         byte[] iv = new byte[16];
         IvParameterSpec ivspec = new IvParameterSpec(iv);
         SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
         KeySpec spec = new PBEKeySpec(key.toCharArray(), "Ganesh998ManuOne".getBytes(), 65536, 256);
         SecretKey tmp = factory.generateSecret(spec);
         SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
         Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
         cipher.init(2, secretKey, ivspec);
         String ss = new String(cipher.doFinal(Base64.getDecoder().decode(strDecrypt)));
         return ss;
      } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | InvalidAlgorithmParameterException var10) {
         logger.info("License Validation Failed");
         return "N";
      } catch (Exception var11) {
         logger.info("License Validation Failed " + var11.getMessage());
         return "N";
      }
   }

   public static String decrypt(String strToDecrypt) {
      try {
         byte[] iv = new byte[16];
         IvParameterSpec ivspec = new IvParameterSpec(iv);
         SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
         KeySpec spec = new PBEKeySpec(getMacAddress().toCharArray(), "Ganesh998ManuOne".getBytes(), 65536, 256);
         SecretKey tmp = factory.generateSecret(spec);
         SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
         Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
         cipher.init(2, secretKey, ivspec);
         return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
      } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | InvalidAlgorithmParameterException var8) {
         logger.info("Error occured during decryption: " + var8.getMessage());
         return null;
      }
   }
}
