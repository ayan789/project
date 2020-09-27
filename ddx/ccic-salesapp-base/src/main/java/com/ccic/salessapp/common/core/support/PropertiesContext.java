package com.ccic.salessapp.common.core.support;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class PropertiesContext {
    @Autowired
    Environment env;
    private static Environment environment;

    @PostConstruct
    public final void init() {
        environment = env;
    }

    public final static String getProperty(String key, String defaultVal) {
        String val = System.getProperty(key);
        if (StringUtils.isBlank(val)) {
            val = environment.getProperty(key, defaultVal);
        }
        return val;
    }


    public final static String pk(){
        return getProperty("sonic.platform.public-key","");
    }

    public final static String applicationName(){
        return getProperty("spring.application.name","").toUpperCase(Locale.ENGLISH);
    }

    public final static List<String> freeSevUri(){
        String uris=getProperty("sonic.platform.free.uris","");
        return StringUtils.isNotBlank(uris)?Arrays.asList(uris.split(",")):new ArrayList<>();
    }

    public final static String clientId(){
        return getProperty("sonic.platform.client.app-id","");
    }


    public final static String clientKey(){
        return getProperty("sonic.platform.client.app-key","");
    }


    public final static String clientSecurity(){
        return getProperty("sonic.platform.client.app-security","");
    }


    public final static String gatewayUrl(){
        return getProperty("sonic.platform.gateway-url","");
    }
}
