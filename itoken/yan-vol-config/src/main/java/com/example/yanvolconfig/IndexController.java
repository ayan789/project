package com.example.yanvolconfig;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/home")
    public String index(){
        return "yan-vol-config";
    }
}
