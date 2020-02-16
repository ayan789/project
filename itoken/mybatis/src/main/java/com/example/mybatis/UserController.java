package com.example.mybatis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/userInfo")
    public String toString() {
        return JSONObject.toJSONString(userDao.getByMap(new HashMap<>())) ;
    }
}
