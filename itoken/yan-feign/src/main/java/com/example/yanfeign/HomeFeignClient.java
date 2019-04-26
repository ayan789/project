package com.example.yanfeign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("yan-admin")
@Component
public interface HomeFeignClient {

    @GetMapping("/hello")
    String helloConsumer();

}
