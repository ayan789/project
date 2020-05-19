package com.example.tlyanjiami;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/health")
    public String health() {
        return "tl-yan-jiami up";
    }
}
