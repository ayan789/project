package com.example.yanvolfeign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("yan-vol-main01-bk01")
@Component
public interface HomeFeignClient {

    @GetMapping("/config")
    String helloConsumer();

}
