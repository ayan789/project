package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Value("${fds.yyy}")
    private String hello;

    @RequestMapping("/hello")
    public String from() {
        return this.hello;
    }
    @GetMapping("/home")
    public String index(){
        return "up admin";
    }
}
