package com.example.webproject.core.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
//import java.util.Base64;

public class AESCbc<T>{
//    public static  void  main(String[] args) throws Exception{
//
////        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
////        keyGenerator.init(128);
////        SecretKey secretKey = keyGenerator.generateKey();
//        String iv = "88888888888888";
//        String KET = "sjKLYGF3468SBxZT";
//        String S = "SSS";
//        String sting = encrypt(S, KET, Arrays.toString(new String[]{iv}));
//        System.out.print(sting);
//        String s = decrypt(sting,KET, Arrays.toString(new String[]{iv}));
//        System.out.print(s);
//    }
    public static  <T> String encrypt(T data) throws Exception {
        // 生成随机密钥和IV
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[16];
        byte[] ivBytes = new byte[16];
        random.nextBytes(keyBytes);
        random.nextBytes(ivBytes);
        // 创建密钥和IV的规格
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        // 获取Cipher实例并初始化
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        // 将Map对象转换为JSON字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(data);
        // 加密并返回结果
        byte[] encrypted = cipher.doFinal(json.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(encrypted) + ":" +
                Base64.encodeBase64String(keyBytes) + ":" +
                Base64.encodeBase64String(ivBytes);
    }
    public static  <T> T decrypt(String ciphertext, String keyBase64, String ivBase64) throws Exception {
        byte[] encryptedData = Base64.decodeBase64(ciphertext);
        byte[] decryptKey = Base64.decodeBase64(keyBase64);
        byte[] iv = Base64.decodeBase64(ivBase64);
        if (decryptKey == null) {
            throw new IllegalArgumentException("Key长度必须为16位");
        }
        if (iv == null) {
            throw new IllegalArgumentException("IV长度必须为16位");
        }
        // 创建密钥和IV的规格
        SecretKeySpec keySpec = new SecretKeySpec(decryptKey, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        // 获取Cipher实例并初始化
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] original = cipher.doFinal(encryptedData);
        return (T) original;
    }

}


