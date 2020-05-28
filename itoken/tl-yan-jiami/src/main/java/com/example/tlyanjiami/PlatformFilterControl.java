package com.example.tlyanjiami;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlatformFilterControl {

    @Autowired
    FilterProperties properties;

    public List<String> notDecryptUri() {
        return from(properties.getNotDecryptUri());
    }

    public boolean decryptSwitch() {
        return properties.isDecryptSwitch();
    }


    public List<String> notLoginUri() {
        return from(properties.getNotLoginUri());
    }

    public boolean loginSwitch() {
        return properties.isLoginSwitch();
    }


    public List<String> notEncryptUri() {
        return from(properties.getNotEncryptUri());
    }

    public boolean encryptSwitch() {
        return properties.isEncryptSwitch();
    }

    public List<String> specialUri() {
        return from(properties.getSpecialUri());
    }

    private List<String> from(String urls) {
        return StringUtils.isBlank(urls) ? Lists.newArrayList("/error")
                : Lists.newArrayList(StringUtils.split(urls, ","));
    }

    public boolean isSpecial(String requestUri){
        return StringUtils.startsWith(requestUri,"/s3/browse/")||StringUtils.startsWith(requestUri,"/content/find/share/");
    }
}
