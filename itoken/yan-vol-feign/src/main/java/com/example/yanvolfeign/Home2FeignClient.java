package com.example.yanvolfeign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("yan-vol-main02-bk01")
@Component
public interface Home2FeignClient {

    @GetMapping("/config")
    String helloConsumer();

}
