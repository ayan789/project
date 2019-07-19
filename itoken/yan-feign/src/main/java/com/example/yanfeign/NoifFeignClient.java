package com.example.yanfeign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("yan-noif")
@Component
public interface NoifFeignClient {

    @GetMapping("/module_2")
    long module2();



}
