package com.example.demo;

import com.nimbusds.jose.JOSEException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/currency")
public class CurrencyLoginController {

    @GetMapping("/login")
    public String ajaxLogin() throws Exception{
        //KEY双向加密规则
        //获取生成token
        String access_token = "";
        Map<String, Object> map = new HashMap<>();
        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", "uid");
        map.put("deptId", "deptId");
        map.put("userType", "userType");
        map.put("companyId", "companyId");
        //生成时间
        map.put("sta", new Date().getTime());
        //过期时间
        map.put("exp", new Date().getTime()+1000*3600*24*15);
        try {
            access_token = Token.creatToken(map);
            System.out.println("access_token="+access_token);
            return access_token;
        } catch (JOSEException e) {
            System.out.println("生成token失败");
            e.printStackTrace();
        }
        return  "error";
    }
}
