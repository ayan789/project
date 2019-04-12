package com.example.provider.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateServiceController {

    //健康检查
    @GetMapping("/checkHealth")
    public String checkHealth(){
        return "up";
    }

    @GetMapping("/test")
    public String test(@RequestParam String param){
        return "test"+param;
    }
}
