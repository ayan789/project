package com.example.tlyanencrypt.util;

import com.alibaba.fastjson.JSONObject;
import com.example.tlyanencrypt.dto.BookDto;
import com.example.tlyanencrypt.dto.Insure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Date;

public class Des {

    /**
     * 对给定的字符串以指定的编码方式和密钥进行加密
     * @param srcStr 待加密的字符串
     * @param charset 字符集，如utf8
     * @param sKey 密钥
     */
    public static String encrypt(String srcStr, Charset charset, String sKey) {
        byte[] src = srcStr.getBytes(charset);
        byte[] buf = Des.encrypt(src, sKey);
        return Des.parseByte2HexStr(buf);
    }

    /**
     * 对给定的密文以指定的编码方式和密钥进行解密
     * @param hexStr 需要解密的密文
     * @param charset 字符集
     * @param sKey 密钥
     * @return 解密后的原文
     * @throws Exception
     */
    public static String decrypt(String hexStr, Charset charset, String sKey) throws Exception {
        byte[] src = Des.parseHexStr2Byte(hexStr);
        byte[] buf = Des.decrypt(src, sKey);
        return new String(buf, charset);
    }

    public static byte[] encrypt(byte[] data, String sKey) {
        try {
            byte[] key = sKey.getBytes();

            IvParameterSpec iv = new IvParameterSpec(key);
            DESKeySpec desKey = new DESKeySpec(key);

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);

            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);

            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param src
     * @param sKey
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String sKey) throws Exception {
        byte[] key = sKey.getBytes();
        // 初始化向量
        IvParameterSpec iv = new IvParameterSpec(key);
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setName("ABC");
        bookDto.setPrice(199.99);
        bookDto.setCreateTime(new Date());
        Insure insure = new Insure();
        insure.setRider("附加险");
        bookDto.setInsure(insure);
        String abc = JSONObject.toJSONString(bookDto);
        System.out.println(abc);

        System.out.println(Des.encrypt("{\"createTime\":1571389709558,\"id\":1,\"insure\":{\"rider\":\"附加险\"},\"name\":\"ABC\",\"price\":199.99}", Charset.forName("utf8"), "testtest"));
        System.out.println(Des.decrypt("92738491CAA521293E88ED1CF02C0FA940F2FA967CD734C8D928C7BE47D53E77CC7021526C857BA021E3DB86D6B54BE1CB6C196E35EC3D65ABD90FBA4C2DACDAB414B3D2C29BF1CDDEB8A1C5FA97DB9878B1FBDB5F10A1FD7A5D1F69DF735F14A2A3542D72D155864E6BB5427A72DE76A4323DD201AD3D730ED7E10EA2B23D0FC859981F0080510FB807D02BC4FFC3EB", Charset.forName("utf8"), "testtest"));
        System.out.println(Des.decrypt("5D2F77C808F0E5B8E17B9EA98523F73690FA53FB14C61084C925AFD5AFADB9CDB313D0F6AA81E6929ACDA733988CC9899B6641352F53F00B3DFA6E8760F4E267979AB49C29A38A5B9B6E7EF772B055A0568A38DA9A29A51FD109C129598B10F7", Charset.forName("utf8"), "testtest"));
    }


    //都不加注解 请求加密 返回加密
    //加了注解   请求解密 返回解密
    //{"createTime":1571389709558,"id":1,"insure":{"rider":"附加险"},"name":"ABC","price":199.99}
    //5D2F77C808F0E5B8E17B9EA98523F73690FA53FB14C61084C925AFD5AFADB9CDB313D0F6AA81E6929ACDA733988CC9899B6641352F53F00B3DFA6E8760F4E267979AB49C29A38A5B9B6E7EF772B055A0568A38DA9A29A51FD109C129598B10F7
}