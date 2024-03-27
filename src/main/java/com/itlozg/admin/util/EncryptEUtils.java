package com.itlozg.admin.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 加密解密
 */
public class EncryptEUtils {

    /**
     * 加密
     *
     * @param message 原文
     * @param key     密钥
     * @return
     * @throws Exception
     */
    public static String EncryptAsDoNet(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] encryptbyte = cipher.doFinal(message.getBytes());
        return new String(new BASE64Encoder().encode(encryptbyte));
    }

    /**
     * 解密
     *
     * @param message 密文
     * @param key     密钥
     * @return
     * @throws Exception
     */
    public static String DecryptDoNet(String message, String key) throws Exception {

//        try {
//            //增加前一天处理
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(new Date());
//            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
//            Date date = calendar.getTime();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            String dateBeforeStr = sdf.format(date);
//
//            byte[] bytesrc = new BASE64Decoder().decodeBuffer(message);
//            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//            DESKeySpec desKeySpec = new DESKeySpec(dateBeforeStr.getBytes("UTF-8"));
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
//            IvParameterSpec iv = new IvParameterSpec(dateBeforeStr.getBytes("UTF-8"));
//            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
//            byte[] retByte = cipher.doFinal(bytesrc);
//            return new String(retByte);
//        } catch (Exception ex) {
//
//        }

        byte[] bytesrc = new BASE64Decoder().decodeBuffer(message);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);

    }
}
