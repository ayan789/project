package com.example.yanrabbitmqproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    Sender sender;

    @GetMapping("/home")
    public String home(){
        return "rabbit producer up";
    }

    @GetMapping("/up")
    public String up(){
        sender.send();
        return "rabbit producer send";
    }
}
