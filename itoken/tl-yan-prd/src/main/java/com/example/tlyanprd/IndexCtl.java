package com.example.tlyanprd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexCtl {
    @Autowired
    UserDao userDao;

    @ResponseBody
    @GetMapping("/aa")
    public List<User> getUserDao() {
        Map<String, Object> map = new HashMap<>();
        return userDao.getByMap(map);
    }
}
