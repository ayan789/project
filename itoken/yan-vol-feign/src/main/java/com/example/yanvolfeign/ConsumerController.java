package com.example.yanvolfeign;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private HomeFeignClient homeFeignClient;

    @Autowired
    private Home2FeignClient home2FeignClient;

    @GetMapping(value = "/hello")
    public String hello() {
        long start = System.currentTimeMillis();
        String str =  homeFeignClient.helloConsumer();
        System.out.println(str);
        String str2 =  home2FeignClient.helloConsumer();
        System.out.println(str2);
        long end = System.currentTimeMillis();
        String time = "总调用时间是：" + (end - start) + "毫秒";
        System.out.println(time);
        return "ok";

    }

}
