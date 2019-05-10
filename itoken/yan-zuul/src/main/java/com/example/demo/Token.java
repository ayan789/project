package com.example.demo;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Token {
    private static final Logger log = LoggerFactory.getLogger(Token.class);
    /**
     * 1.创建一个32-byte的密匙JWT生成TOKEN
     */
    private static final byte[] secret = "geiwodiangasfdjsikolkjikolkijswe".getBytes();
    //生成一个token
    public static String creatToken(Map<String,Object> payloadMap) throws JOSEException {
        //3.先建立一个头部Header
        /**
         * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
         * 一般只需要传入加密算法法则就可以。
         * 这里则采用HS256
         * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
         */
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap));
        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //建立一个密匙
        JWSSigner jwsSigner = new MACSigner(secret);
        //签名
        jwsObject.sign(jwsSigner);
        //生成token
        return jwsObject.serialize();
    }
    /**
     * 解析一个token
     * @param token
     * @return
     * @throws ParseException
     * @throws JOSEException
     */
    public static Map<String,Object> valid(String token) throws ParseException, JOSEException {
        //解析token
        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(token);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        //获取到载荷
        Payload payload=jwsObject.getPayload();
        //建立一个解锁密匙
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        Map<String, Object> resultMap = new HashMap<>();
        //判断token
        if (jwsObject.verify(jwsVerifier)) {
            resultMap.put("Result", 0);
            //载荷的数据解析成json对象。
            JSONObject jsonObject = payload.toJSONObject();
            resultMap.put("data", jsonObject);
            //判断token是否过期
            if (jsonObject.containsKey("exp")) {
                Long expTime = Long.valueOf(jsonObject.get("exp").toString());
                Long nowTime = new Date().getTime();
                //判断是否过期
                if (nowTime > expTime) {
                    //已经过期
                    resultMap.clear();
                    resultMap.put("Result", 2);
                }
            }
        }else {
            resultMap.put("Result", 1);
        }
        return resultMap;
    }
    /**
     * 生成token的业务逻辑 登录接口调用次业务
     * @param uid
     * @return
     */
    public static String TokenTest(Long uid,Long deptId,String userType,int companyId) {
        //获取生成token
        Map<String, Object> map = new HashMap<>();
        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", uid);
        map.put("deptId", deptId);
        map.put("userType", userType);
        map.put("companyId", companyId);
        //生成时间
        map.put("sta", new Date().getTime());
        //过期时间
        map.put("exp", new Date().getTime()+1000*3600*24*15);
        try {
            String token = Token.creatToken(map);
            System.out.println("token="+token);
            return token;
        } catch (JOSEException e) {
            System.out.println("生成token失败");
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 处理解析的业务逻辑 gateway JWT认证过滤器解析
     * @param token
     */
    public static Map<String,Object> ValidToken(String token) {
        Map<String, Object> userMsg = new HashMap<String, Object>();
        //解析token
        try {
            if (token != null) {
                Map<String, Object> validMap = Token.valid(token);
                int i = (int) validMap.get("Result");
                if (i == 0) {
                    log.info("token解析成功");
                    JSONObject jsonObject = (JSONObject) validMap.get("data");
                    log.info("uid是：" + jsonObject.get("uid"));
                    log.info("deptId是：" + jsonObject.get("deptId"));
                    log.info("userType是：" + jsonObject.get("userType"));
                    log.info("companyId是：" + jsonObject.get("companyId"));
                    log.info("生成时间是："+jsonObject.get("sta"));
                    log.info("过期时间是："+jsonObject.get("exp"));
                    userMsg.put("token",token);
                    userMsg.put("uid",jsonObject.get("uid"));
                    userMsg.put("deptId",jsonObject.get("deptId"));
                    userMsg.put("companyId",jsonObject.get("companyId"));
                    userMsg.put("userType",jsonObject.get("userType"));
                    return userMsg;
                } else if (i == 2) {
                    log.info("token已经过期");
                    return userMsg;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return userMsg;
    }

    public static void main(String[] ages) {
        //获取token
        Long uid = 1L;
        Long deptId = 2L;
        String userType = "3";
        int companyId = 4;
        String token = TokenTest(uid,deptId,userType,companyId);
        //解析token
        log.info(ValidToken(token).toString());
    }
}
