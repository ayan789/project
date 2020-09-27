package com.ccic.salessapp.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



 

public class RSAUtils {
	private static Log log=LogFactory.getLog(RSAUtils.class);
	private static final String ALGORITHM = "RSA"; 
	private static final String PADDING = "RSA/ECB/PKCS1Padding";
	/**
     * RSA最大加密明文大小
     */
    private static int MAX_ENCRYPT_BLOCK = 117;
     
    /**
     * RSA最大解密密文大小
     */
    private static int MAX_DECRYPT_BLOCK = 128;
	/** 
     * 公钥 
     */  
    private static RSAPublicKey publicKey;  
   /**
    * 
    * 私钥
    * */
    private static RSAPrivateKey privateKey;
    
    static{
    	try {
    		//初始化时加载密钥
			loadKey();
			MAX_ENCRYPT_BLOCK = privateKey.getModulus().bitLength() / 8-11;
			MAX_DECRYPT_BLOCK = privateKey.getModulus().bitLength() / 8;
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * 加载密钥
     * */
	public static void loadKey() throws Exception{
		InputStream publicInput = null;
    	InputStream privateInput = null;
    	try {
    		//服务端不需要加载公钥
    		publicInput = Thread.currentThread().getContextClassLoader().getResourceAsStream("rsa_public_key.pem");
			loadPublicKey(publicInput);
        	
    		privateInput = Thread.currentThread().getContextClassLoader().getResourceAsStream("private_key.pem");
			loadPrivateKey(privateInput);
			
		} catch (Exception e) {
			log.error("加载RSA密钥失败",e);
			throw new Exception("加载RSA密钥失败");
		}finally{
			if(publicInput!=null){
				try {
					publicInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
			if(privateInput!=null){
				try {
					privateInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
		}
	}
	
	/** 
     * 从公钥文件中加载公钥
     * @throws Exception 加载公钥时产生的异常 
     */  
    public static void loadPublicKey(InputStream in) throws Exception{
    	BufferedReader br = null;
    	InputStreamReader inRead = null;
        try{
        	 inRead = new InputStreamReader(in);
        	 br= new BufferedReader(inRead); 
             String readLine= null;  
             StringBuilder sb= new StringBuilder();  
             while((readLine= br.readLine())!=null){  
                 if(readLine.charAt(0)=='-'){  
                     continue;  
                 }else{  
                     sb.append(readLine);  
                     sb.append('\r');  
                 }  
             }  
             loadPublicKey(sb.toString());  
        }catch(Exception e){
        	log.error("加载公钥失败！",e);
        	throw new Exception("加载公钥失败！",e);
        }finally{
        	if(br!=null){
        		br.close();
        	}
        	if(inRead!=null){
        		inRead.close();
        	}
        }
    }  
    
    /** 
     * 从字符串中加载公钥 
     * @param publicKeyStr 公钥数据字符串 
     * @throws Exception 加载公钥时产生的异常 
     */  
    public static void loadPublicKey(String publicKeyStr) throws Exception{  
        try {  
            BASE64Decoder base64Decoder= new BASE64Decoder();  
            byte[] buffer= base64Decoder.decodeBuffer(publicKeyStr);  
            KeyFactory keyFactory= KeyFactory.getInstance(ALGORITHM);  
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);  
            publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);  
        } catch (Exception e) {  
        	log.error("加载公钥失败！",e);
            throw new Exception("加载公钥失败！",e);  
        }  
    }  
    
    
    
    /** 
     * 从私钥文件中加载私钥
     * @throws Exception 加载私钥时产生的异常 
     */  
    public static void loadPrivateKey(InputStream in) throws Exception{
    	BufferedReader br = null;
    	InputStreamReader inRead = null;
        try{
        	 inRead = new InputStreamReader(in);
        	 br= new BufferedReader(inRead); 
             String readLine= null;  
             StringBuilder sb= new StringBuilder();  
             while((readLine= br.readLine())!=null){  
                 if(readLine.charAt(0)=='-'){  
                     continue;  
                 }else{  
                     sb.append(readLine);  
                     sb.append('\r');  
                 }  
             }  
             loadPrivateKey(sb.toString());  
        }catch(Exception e){
        	log.error("加载私钥失败！",e);
        	throw new Exception("加载私钥失败！",e);
        }finally{
        	if(br!=null){
        		br.close();
        	}
        	if(inRead!=null){
        		inRead.close();
        	}
        }
    }  
    /** 
     * 从字符串中加载私钥
     * @param publicKeyStr 私钥数据字符串 
     * @throws Exception 加载私钥时产生的异常 
     */  
    public static void loadPrivateKey(String privateKeyStr) throws Exception{  
        try {  
            BASE64Decoder base64Decoder= new BASE64Decoder();  
            byte[] buffer= base64Decoder.decodeBuffer(privateKeyStr);  
            PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);  
            KeyFactory keyFactory= KeyFactory.getInstance(ALGORITHM);  
            privateKey= (RSAPrivateKey) keyFactory.generatePrivate(keySpec);  
        } catch (Exception e) {  
        	log.error("加载私钥失败！",e);
            throw new Exception("加载私钥失败！");  
        }  
    }  
 
    /** 
     * 使用公钥加密 
     * @param content 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptByPublic(String content) throws Exception {  
        try {  
            Cipher cipher = Cipher.getInstance(PADDING);  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
   
            byte encryptedData[] = content.getBytes("UTF-8");
            
            int inputLen = encryptedData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptedData, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] output = out.toByteArray();  
            out.close();
   
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String s = new String(base64Encoder.encode(output));  
            return s;  
   
        } catch (Exception e) {  
        	log.error("加密明文失败！",e);
            throw new Exception("加密明文失败！",e);
        }
    } 
    
    /** 
     * 使用公钥解密 
     * @param content 密文 
     * @param key 商户私钥 
     * @return 解密后的字符串 
     * @throws Exception 
     */  
     public static String decryptByPublic(String content) throws Exception {  
         try {  
        	 BASE64Decoder base64Decoder= new BASE64Decoder(); 
             Cipher cipher = Cipher.getInstance(PADDING);  
             cipher.init(Cipher.DECRYPT_MODE, publicKey);  
             
             byte[] decryptedData = base64Decoder.decodeBuffer(content);
             int inputLen = decryptedData.length;
             ByteArrayOutputStream out = new ByteArrayOutputStream();
             int offSet = 0;
             byte[] cache;
             int i = 0;
             // 对数据分段解密
             while (inputLen - offSet > 0) {
                 if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                     cache = cipher.doFinal(decryptedData, offSet, MAX_DECRYPT_BLOCK);
                 } else {
                     cache = cipher.doFinal(decryptedData, offSet, inputLen - offSet);
                 }
                 out.write(cache, 0, cache.length);
                 i++;
                 offSet = i * MAX_DECRYPT_BLOCK;
             }
             String str = new String(out.toByteArray(), "utf-8");
             out.close();
             return str;  
         } catch (Exception e) {  
        	 log.error("公钥解密明文失败！",e);
        	 throw e;
         }  
     } 
     
     /** 
      * 使用私钥加密 
      * @param content 
      * @param key 
      * @return 
      * @throws Exception 
      */  
     public static String encryptByPrivate(String content) throws Exception {  
         try {  
             Cipher cipher = Cipher.getInstance(PADDING);  
             cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
    
             byte encryptedData[] = content.getBytes("UTF-8");
             
             int inputLen = encryptedData.length;
             ByteArrayOutputStream out = new ByteArrayOutputStream();
             int offSet = 0;
             byte[] cache;
             int i = 0;
             // 对数据分段加密
             while (inputLen - offSet > 0) {
                 if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                     cache = cipher.doFinal(encryptedData, offSet, MAX_ENCRYPT_BLOCK);
                 } else {
                     cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                 }
                 out.write(cache, 0, cache.length);
                 i++;
                 offSet = i * MAX_ENCRYPT_BLOCK;
             }
             byte[] output = out.toByteArray();  
             out.close();
             BASE64Encoder base64Encoder = new BASE64Encoder();
             String s = new String(base64Encoder.encode(output));  
             return s;  
    
         } catch (Exception e) {  
        	 log.error("私钥加密明文失败！",e);
             throw new Exception("私钥加密明文失败！",e);
         }  
     } 
     
     
     /** 
      * 使用私钥解密 
      * @param content 密文 
      * @param key 商户私钥 
      * @return 解密后的字符串 
     * @throws Exception 
      */  
      public static String decryptByPrivate(String content) throws Exception {  
          try {  
         	 BASE64Decoder base64Decoder= new BASE64Decoder(); 
              Cipher cipher = Cipher.getInstance(PADDING);  
              cipher.init(Cipher.DECRYPT_MODE, privateKey);  
              byte[] decryptedData = base64Decoder.decodeBuffer(content);
              int inputLen = decryptedData.length;
              ByteArrayOutputStream out = new ByteArrayOutputStream();
              int offSet = 0;
              byte[] cache;
              int i = 0;
              // 对数据分段解密
              while (inputLen - offSet > 0) {
                  if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                      cache = cipher.doFinal(decryptedData, offSet, MAX_DECRYPT_BLOCK);
                  } else {
                      cache = cipher.doFinal(decryptedData, offSet, inputLen - offSet);
                  }
                  out.write(cache, 0, cache.length);
                  i++;
                  offSet = i * MAX_DECRYPT_BLOCK;
              }
              String str = new String(out.toByteArray(), "utf-8");  
              out.close();
              return str;  
          } catch (Exception e) {  
        	  log.error("私钥解密密文失败！",e); 
        	  throw e;
          }  
      } 
      

      
    
 
}
