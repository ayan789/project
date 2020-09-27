package com.ccic.salesapp.gateway.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class GatewayFallbackController {

    @RequestMapping(value = "fallback",method = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
    public Object fallback() {
        Map<String,Object> data=new HashMap<>();
        data.put("code","G1002");
        data.put("msg","系统繁忙，请稍后再试！");
        return data;
    }
}
