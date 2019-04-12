package com.example.consumerfeign;

import org.springframework.stereotype.Component;

@Component
public class TestFeignFallback implements DateServiceFeignClientInterface {
    @Override
    public String consumer(String param) {
        return "service is Down";
    }
}
