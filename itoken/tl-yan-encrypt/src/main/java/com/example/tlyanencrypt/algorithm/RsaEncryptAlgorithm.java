package com.example.tlyanencrypt.algorithm;

import com.example.tlyanencrypt.util.Base64Utils;
import com.example.tlyanencrypt.util.Des;
import org.springframework.stereotype.Component;
import com.cxytiandi.encrypt.algorithm.EncryptAlgorithm;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;

@Component
public class RsaEncryptAlgorithm implements EncryptAlgorithm {

    public String encrypt(String content, String encryptKey) throws Exception {
        if(!StringUtils.isEmpty(content)) {
            System.out.println("解密:" + content + encryptKey);
//        return Base64Utils.encode(content);
            return Des.encrypt(content, Charset.forName("utf8"), encryptKey);
        }
        else{
            return "";
        }
    }

    public String decrypt(String encryptStr, String decryptKey) throws Exception {
        if(!StringUtils.isEmpty(encryptStr)) {
        System.out.println("加密:"+encryptStr+decryptKey);
//        return Base64Utils.decode(encryptStr);
        return Des.decrypt(encryptStr, Charset.forName("utf8"),decryptKey);
        }
        else{
            return "";
        }
    }

}