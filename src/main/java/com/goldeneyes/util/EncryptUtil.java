/*----------------------------------------------------------------
 *  Copyright (C) 2016山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：EncryptUtil
 * 文件功能描述：加密工具类
 *
 * 
 * 创建标识：konglm20161026
 *
 * 修改标识：
 * 修改描述：
 *----------------------------------------------------------------*/
package com.goldeneyes.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
 
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class EncryptUtil {
 
    public static void main(String[] args) throws Exception {
    	System.out.println(desEncrypt("admin","jsy11111"));
    	System.out.println(desDecrypt("JKG5DkSGuOA=","jsy11111"));
    	System.out.println(getBase64("admin"));
    	System.out.println(getFromBase64("YWRtaW4="));
    	System.out.println(getMd5("admin"));
    	
    }
     
    /**
     * DES加密
     * @param inputData
     * @param inputKey
     * @return
     * @throws Exception
     */
  	public static String desEncrypt(String inputData, String inputKey)
  			throws Exception {
  		byte[] DESkey = inputKey.getBytes();// 设置密钥
  		DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
  		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
  		Key key = keyFactory.generateSecret(keySpec);// 得到密钥对象

  		Cipher enCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");// 得到加密对象Cipher
  		enCipher.init(Cipher.ENCRYPT_MODE, key);// 设置工作模式为加密模式，给出密钥和向量
  		byte[] pasByte = enCipher.doFinal(inputData.getBytes("utf-8"));
  		BASE64Encoder base64Encoder = new BASE64Encoder();
  		return base64Encoder.encode(pasByte);
  	}

  	/**
  	 * DES解密
  	 * @param inputData
  	 * @param inputKey
  	 * @return
  	 * @throws Exception
  	 */
  	public static String desDecrypt(String inputData, String inputKey)
  			throws Exception {
  		BASE64Decoder base64Decoder = new BASE64Decoder();
  		byte[] byteInputData = base64Decoder.decodeBuffer(inputData);

  		byte[] DESkey = inputKey.getBytes();// 设置密钥
  		DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
  		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
  		Key key = keyFactory.generateSecret(keySpec);// 得到密钥对象

  		// using DES in ECB mode
  		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
  		// 用密匙初始化Cipher对象
  		cipher.init(Cipher.DECRYPT_MODE, key);
  		byte decryptedData[] = cipher.doFinal(byteInputData);
  		String value = new String(decryptedData, "UTF-8");
  		return value;// decryptedData;
  	}
  	
  	/**
  	 * Base64加密转换
  	 * @param str
  	 * @return
  	 */
    public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    /**
     * Base64解密转换 
     * @param s
     * @return
     */
    public static String getFromBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  
    
    /**
     * MD5加密
     * @param plainText
     * @return
     */
    public static String getMd5(String plainText) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(plainText.getBytes());  
            byte b[] = md.digest();  
  
            int i;  
  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0)  
                    i += 256;  
                if (i < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }  
            //32位加密  
            return buf.toString();  
            // 16位的加密  
            //return buf.toString().substring(8, 24);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return null;  
        }  
  
    }  
}
