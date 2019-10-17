package com.example.tlyanencrypt.algorithm;

import com.example.tlyanencrypt.util.Base64Utils;
import org.springframework.stereotype.Component;
import com.cxytiandi.encrypt.algorithm.EncryptAlgorithm;

@Component
public class RsaEncryptAlgorithm implements EncryptAlgorithm {

    public String encrypt(String content, String encryptKey) throws Exception {
        System.out.println("解密:"+content+encryptKey);
        return Base64Utils.encode(content);
    }

    public String decrypt(String encryptStr, String decryptKey) throws Exception {
        System.out.println("加密:"+encryptStr+decryptKey);
        System.out.println(Base64Utils.decode(encryptStr));
        return Base64Utils.decode(encryptStr);
    }

}