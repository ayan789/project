package com.example.yanvolmain01bk01;

import com.example.yanvolcommon.YanVolCommonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class IndexController {
    @Value("${fds.yyy}")
    private String hello;

    @Value("${server.port}")
    String port;

    @GetMapping("/home")
    public String index(){
        return "yan-vol-main01-bk01";
    }

    @RequestMapping("/config")
    public String config() {
        YanVolCommonUtils yanVolCommonUtils = new YanVolCommonUtils();
        return this.hello+",port:" + port + yanVolCommonUtils.getYanVolCommonUtils();
    }


}
