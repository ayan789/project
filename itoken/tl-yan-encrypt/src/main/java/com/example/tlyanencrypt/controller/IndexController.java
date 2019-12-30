package com.example.tlyanencrypt.controller;

import com.example.tlyanencrypt.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController extends BaseController {

    @GetMapping("/hello2")
    public Object hello2() {
        start();
        success(true);
        params("key1","jjjj");
        return end();

    }

}
