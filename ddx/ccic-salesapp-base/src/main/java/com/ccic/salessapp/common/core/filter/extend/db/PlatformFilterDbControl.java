package com.ccic.salessapp.common.core.filter.extend.db;

import com.ccic.salessapp.common.core.filter.extend.FilterProperties;
import com.ccic.salessapp.common.service.RedisService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PlatformFilterDbControl {

    final static String NO_ENCRYPT="NO_ENCRYPT_URI";
    final static String NO_DECRYPT="NO_DECRYPT_URI";
    final static String NO_LOGIN="NO_LOGIN_URI";

    final static String IS_ENCRYPT="IS_ENCRYPT";
    final static String IS_DECRYPT="IS_DECRYPT";
    final static String IS_LOGIN="IS_LOGIN";

    final static String CONST_REDIS="const:info";

    final static String key="constKey";
    final static String value="constValue";


    @Autowired
    RedisService redisService;
    @Autowired
    SysConstMapper constMapper;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    Environment environment;

    @PostConstruct
    public void init(){
        String appName=environment.getProperty("spring.application.name","");
        List<Map<String,String>> data=constMapper.selectByServiceId(appName);
        data.forEach(d->redisService.putDataToCacheMap(CONST_REDIS,d.get(key),d));
    }

    public String getByConstKey(String constKey){
        Map<String,String> data=redisService.getDataFromCacheMap(CONST_REDIS,constKey,Map.class);
        if(CollectionUtils.isEmpty(data)){
            String appName=environment.getProperty("spring.application.name","");
            Map<String,Object> param=new HashMap<>();
            param.put("constKey",constKey);
            param.put("serviceId",appName);
            Map<String,String> dbData=constMapper.selectByServiceIdAndKey(param);
            if(CollectionUtils.isEmpty(dbData))return "";
            redisService.putDataToCacheMap(CONST_REDIS,dbData.get(key),dbData);
            return dbData.get(value);
        }
        return data.get(value);
    }

    @Autowired
    FilterProperties properties;

    public boolean notDecryptUri(String uri) {
        return isSpecial(uri)||match(uri,getByConstKey(NO_DECRYPT));
    }

    public boolean decryptSwitch() {
        return StringUtils.equals("Y",getByConstKey(IS_DECRYPT));
    }

    public boolean notLoginUri(String uri) {
        return isSpecial(uri)||match(uri,getByConstKey(NO_LOGIN));
    }

    public boolean loginSwitch() {
        return StringUtils.equals("Y",getByConstKey(IS_LOGIN));
    }


    public boolean notEncryptUri(String uri) {
        return isSpecial(uri)||match(uri,getByConstKey(NO_ENCRYPT));
    }

    public boolean encryptSwitch() {
        return StringUtils.equals("Y",getByConstKey(IS_ENCRYPT));
    }

    /**
     * @param uri
     * @return
     */
    public boolean isSpecial(String uri) {
        String regex = "^/((swagger-resources)|(webjars)|(actuator))|((\\.(jpg|html|css|js|png|gif|ico|jpeg))|(/error|/v2/api-docs))$";
        return match(uri,regex);
    }


    private boolean match(String uri,String regex){
        if(StringUtils.isBlank(uri)||StringUtils.isBlank(regex))return true;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(uri);
        return matcher.find();
    }
}
