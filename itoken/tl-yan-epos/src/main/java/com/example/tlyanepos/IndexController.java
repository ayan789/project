package com.example.tlyanepos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
public class IndexController {

    @Autowired
    UserDao userDao;

    @GetMapping("/home")
    public String index () {
        return "up tl-yan-epos";
    }

    @GetMapping("/userList")
    public String userList () {
         List<User> list = userDao.getByMap(new HashMap<>());
         System.out.println(list.get(0).getName());
         return "OK";
    }

    @GetMapping("/getDemo")
    public String getDemo (String a,String b) {
        System.out.println("a"+a);
        System.out.println("b"+b);
        return "OK";
    }

    @GetMapping("/postDemo")
    public String postDemo (String a,String b) {
        System.out.println("a"+a);
        System.out.println("b"+b);
        return "OK";
    }

}
