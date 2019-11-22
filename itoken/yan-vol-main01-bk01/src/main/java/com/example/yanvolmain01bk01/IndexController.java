package com.example.yanvolmain01bk01;

import com.example.yanvolcommon.YanVolCommonUtils;
import com.example.yanvolmain01bk01.common.utils.Query;
import com.example.yanvolmain01bk01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
public class IndexController {
    @Value("${fds.yyy}")
    private String hello;

    @Value("${server.port}")
    String port;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String index(){
        return "yan-vol-main01-bk01";
    }

    @RequestMapping("/config")
    public String config(){
        Map<String, Object> params = new HashMap<>();
        Query query = new Query(params);
        System.out.println(userService.list(query).get(0).getName());
        YanVolCommonUtils yanVolCommonUtils = new YanVolCommonUtils();
        return this.hello+",port:" + port + yanVolCommonUtils.getYanVolCommonUtils();
    }

//    @RequestMapping("/getUser")
//    public String getUser() {
//        return userService.getById(1).getName();
//    }


}
