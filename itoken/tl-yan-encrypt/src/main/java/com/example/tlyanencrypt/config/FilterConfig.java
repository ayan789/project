package com.example.tlyanencrypt.config;


import java.util.Arrays;

import com.example.tlyanencrypt.algorithm.RsaEncryptAlgorithm;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cxytiandi.encrypt.core.EncryptionConfig;
import com.cxytiandi.encrypt.core.EncryptionFilter;

@Configuration
public class FilterConfig {

    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public FilterRegistrationBean filterRegistration() {
    	EncryptionConfig config = new EncryptionConfig();
    	config.setKey("testtest");
    	FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new EncryptionFilter(config,new RsaEncryptAlgorithm()));
        registration.addUrlPatterns("/*");
        registration.setName("EncryptionFilter");
        registration.setOrder(1);
        return registration;
    }

}