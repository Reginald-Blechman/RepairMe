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

public class FacilitateData {
   static Logger logger = Logger.getLogger(MyLogger.class);
   private static final String SALTVALUE = "Ganesh998ManuOne";

   public static String getMacAddress() {
      String userID = "";

      try {
         userID = BackServiceEncryptData.getMacAddress1();
      } catch (Exception var4) {
         var4.getMessage();
      }

      String UserID_1 = userID.substring(userID.length() - 3);
      String UserID_2 = userID.substring(0, 3);
      String key = UserID_1 + "NU" + UserID_2 + "MA";
      return key.toUpperCase();
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
         var10.printStackTrace();
         System.out.println("License Validation Failed");
         logger.info("License Validation Failed");
         return "N";
      } catch (Exception var11) {
         var11.printStackTrace();
         System.out.println("License Failed");
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
         System.out.println("Error occured during decryption: " + var8.toString());
         return null;
      }
   }
}
