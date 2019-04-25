package com.example.demo;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
public class IndexController {
    @GetMapping("/home")
    public String index(){
        return "up config";
    }
}
