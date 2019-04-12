package com.example.consumerfeign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider",fallback = TestFeignFallback.class)
public interface DateServiceFeignClientInterface {
    @GetMapping("/test")
    String consumer(@RequestParam("param") String param );
}
