package com.unbank.duplicate.util;

import javax.crypto.Cipher;      
import javax.crypto.SecretKey;      
import javax.crypto.SecretKeyFactory;      
import javax.crypto.spec.DESKeySpec;      
import javax.crypto.spec.IvParameterSpec;      

     
     
public class DESUtil {      
	public static final String key="yzmimaHH";;
          
    //解密数据      
    public static String decrypt(String message,String key) throws Exception {      
               
            byte[] bytesrc =convertHexString(message);         
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");          
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));         
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");         
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);         
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));      
                      
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);            
                
            byte[] retByte = cipher.doFinal(bytesrc);           
            return new String(retByte);       
    }      
    
    //加密数据
    public static byte[] encrypt(String message, String key)      
            throws Exception {      
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");      
     
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));      
     
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");      
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);      
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));      
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);      
     
        return cipher.doFinal(message.getBytes("UTF-8"));      
    }      
          
    public static byte[] convertHexString(String ss)       
    {       
    byte digest[] = new byte[ss.length() / 2];       
    for(int i = 0; i < digest.length; i++)       
    {       
    String byteString = ss.substring(2 * i, 2 * i + 2);       
    int byteValue = Integer.parseInt(byteString, 16);       
    digest[i] = (byte)byteValue;       
    }       
     
    return digest;       
    }       
     
     
    public static void main(String[] args) throws Exception {      
        
        String content="http://3g.wocwol.com";
        String a  = toHexString(encrypt(content,key));
        System.out.println("加密后的数据为:"+a);      
        String b=java.net.URLDecoder.decode(decrypt(a,key), "utf-8") ;      
        System.out.println("解密后的数据:"+b);      
     
    }      
     
    public static String toHexString(byte b[]) {      
        StringBuffer hexString = new StringBuffer();      
        for (int i = 0; i < b.length; i++) {      
            String plainText = Integer.toHexString(0xff & b[i]);      
            if (plainText.length() < 2)      
                plainText = "0" + plainText;      
            hexString.append(plainText);      
        }      
              
        return hexString.toString();      
    }      
     
}