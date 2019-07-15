package com.example.tlyannoif;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {



    @GetMapping("/home")
    public String index () {
        return "up tl-yan-noif";
    }

    @GetMapping("/noif")
    public String noif () throws Exception{
        Context context = new Context();
        String con = context.calRecharge(1,1);
        return con;
    }
}
