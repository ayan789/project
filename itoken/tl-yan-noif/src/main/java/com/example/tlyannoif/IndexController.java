package com.example.tlyannoif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String index () {
        return "up tl-yan-noif";
    }

    @GetMapping("/noif/{id}")
    public String noif (@PathVariable("id") int id) throws Exception{
        Context context = new Context();
        String con = context.calRecharge(id,1);
        return con;
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable("id") int id){
        User user = userService.getById(id);
        return user ;
    }
}
