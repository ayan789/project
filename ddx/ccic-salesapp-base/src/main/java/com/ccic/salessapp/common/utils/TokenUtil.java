package com.ccic.salessapp.common.utils;

import com.ccic.salessapp.common.core.CoreContants;
import com.ccic.salessapp.common.core.support.RequestContext;
import com.ccic.salessapp.common.request.UserToken;
import com.ccic.salessapp.common.service.RedisService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenUtil {

    private static RedisService redisService;


    public static void init(RedisService redis) {
        redisService = redis;
    }

    public static UserToken getToken() {
        String token = RequestContext.getHeader(CoreContants.X_ACCESS_TOKEN);
        log.info("header token {}", token);
        UserToken result = redisService.getAccessToken(token);
        if (null != result) log.info("TokenUtil getToken ,token {},tokenInfo {}", token, new Gson().toJson(result));        
        return result;
    }
    public static UserToken getUserTokenByUserCode(String userCode) {
    	log.info("getUserTokenByUserCode userCode is {}", userCode);
    	UserToken result = redisService.getAccessTokenByUserCode(userCode);
    	if (null != result) {
    		log.info("TokenUtil getUserTokenByUserCode from REDIS,userCode {},tokenInfo {}", userCode, new Gson().toJson(result));
    	}
    	return result;
    }
}
