package com.example.yanvolfeign.algorithm;

import com.cxytiandi.encrypt.algorithm.EncryptAlgorithm;
import com.example.yanvolfeign.R;
import com.example.yanvolfeign.util.Des;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;

@Component
public class RsaEncryptAlgorithm implements EncryptAlgorithm {

    public String encrypt(String content, String encryptKey) throws Exception {
        if (!StringUtils.isEmpty(content)) {
            System.out.println("解密:" + content + encryptKey);
//        return Base64Utils.encode(content);
            return Des.encrypt(content, Charset.forName("utf8"), encryptKey);
        } else {
            return "";
        }
    }

    public String decrypt(String encryptStr, String decryptKey) {
        if (!StringUtils.isEmpty(encryptStr)) {
            System.out.println("加密:" + encryptStr + decryptKey);
//        return Base64Utils.decode(encryptStr);
            try {
                return Des.decrypt(encryptStr, Charset.forName("utf8"), decryptKey);
            } catch (NumberFormatException e) {
                return R.error().data(e.getMessage()).toString();
            } catch (Exception e) {
                return "";
            }

        } else {
            return "";
        }
    }

}