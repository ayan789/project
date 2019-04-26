package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class IndexController {
    @Value("${fds.yyy}")
    private String hello;

    @Value("${server.port}")
    String port;

    @RequestMapping("/hello")
    public String from() {
        return this.hello+",port:" + port;
    }

    @GetMapping("/home")
    public String index(){
        return "up admin";
    }
}
