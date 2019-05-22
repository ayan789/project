package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/home")
    public String index(){
        Test test = new Test();
        System.out.println(test.abc());
        return "up eureka";
    }
}
