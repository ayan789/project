package com.ccic.salesapp.performance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestTemplateConfig {


//    @Bean
//    @ConfigurationProperties(prefix = "custom.rest.connection")
//    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() {
//        return new HttpComponentsClientHttpRequestFactory();
//    }

    @Bean
    public RestTemplate customRestTemplate(){
    	//return new RestTemplate(customHttpRequestFactory());
        return new RestTemplate();
    }

}
