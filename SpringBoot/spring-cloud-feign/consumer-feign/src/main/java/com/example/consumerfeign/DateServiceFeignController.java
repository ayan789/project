package com.example.consumerfeign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class DateServiceFeignController {
    @Resource
    DateServiceFeignClientInterface dateServiceFeignClientInterface;

    @GetMapping("/date")
    public String getDate(@RequestParam("param") String param ){
        return dateServiceFeignClientInterface.consumer(param);
    }
}
