package com.example.tlyanjiami;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @desc AES 加密工具类
 */
@Slf4j
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String CHARSET = "UTF-8";

    public static String encrypt(String content, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        byte[] byteContent = content.getBytes(CHARSET);
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
        byte[] result = cipher.doFinal(byteContent);
        return Base64Utils.encodeToString(result);
    }

    public static String decrypt(String content, String password) throws NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
        byte[] result = cipher.doFinal(Base64Utils.decodeFromString(content));
        return new String(result, CHARSET);
    }

    private static SecretKeySpec getSecretKey(final String password) throws UnsupportedEncodingException {
        return new SecretKeySpec(password.getBytes(CHARSET), KEY_ALGORITHM);
    }

    public static String getKey() throws NoSuchAlgorithmException {
        //return generate(16);
    	return "k4Ea3T650sPdUdM5";
    }

    private static String generate(int length) throws NoSuchAlgorithmException {
        char[] chars = new char[length];
        int i=0;
        SecureRandom secureRandom=SecureRandom.getInstanceStrong();
        while(i<length) {
            int random = secureRandom.nextInt(3);
            chars[i]=(random==0)?(char) ('A'+secureRandom.nextInt(26)):(random==1)?(char) ('a'+secureRandom.nextInt(26)):(char) ('0'+secureRandom.nextInt(10));
            i++;
        }
        return new String(chars);
    }


//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(getKey());
//        }
//    }

    public static String key(){
        return PropertiesUtil.getProperty("SALES_APP_AES_KEY","9CF181E2CD72F8E6");
    }
    
//    public static void main(String[] args) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
//    	String a = AESUtil.encrypt("[{\"aaa\":\"123\",\"bbb\":{\"bb\":\"123\"}},{\"aaa\":\"456\",\"bbb\":{\"bb\":\"456\"}}]", "k4Ea3T650sPdUdM5");
//    	System.out.println(a);
//	}
    
    public static void main(String[] args) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
    	String a = AESUtil.decrypt("L1CcWBQMhOcLjJ9EzPAxMvR/NjnR6WmTIJBNHmLYEH1x/JywFcXq9Utae0rls6fx85V1Sm4ZINq/XvKLtNvVlM8HMhulVxVx4fyzMzCcuPQ=", "k4Ea3T650sPdUdM5");
    	System.out.println(a);
	}
    
    //盐 k4Ea3T650sPdUdM5
    //入餐
    //[{"aaa":"123","bbb":{"bb":"123"}},{"aaa":"456","bbb":{"bb":"456"}}]
    //L1CcWBQMhOcLjJ9EzPAxMvR/NjnR6WmTIJBNHmLYEH1x/JywFcXq9Utae0rls6fx85V1Sm4ZINq/XvKLtNvVlM8HMhulVxVx4fyzMzCcuPQ=
    //出餐
    //L1CcWBQMhOcLjJ9EzPAxMvR/NjnR6WmTIJBNHmLYEH1x/JywFcXq9Utae0rls6fx85V1Sm4ZINq/XvKLtNvVlM8HMhulVxVx4fyzMzCcuPQ=
    //[{"aaa":"123","bbb":{"bb":"123"}},{"aaa":"456","bbb":{"bb":"456"}}]

    
    
    
    
}



