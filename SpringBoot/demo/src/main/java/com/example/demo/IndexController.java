package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class IndexController {
    @GetMapping("/home")
    public String index(Map<String,Object> map){
        map.put("hello","你好 thymeleaf");
        return "index";
    }
}
