package com.example.yanvolfeign;


import com.alibaba.fastjson.JSONObject;
import com.cxytiandi.encrypt.springboot.annotation.Decrypt;
import com.cxytiandi.encrypt.springboot.annotation.Encrypt;
import com.example.yanvolfeign.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController{

    @Autowired
    private HomeFeignClient homeFeignClient;

    @Autowired
    private Home2FeignClient home2FeignClient;

    @Decrypt
    @Encrypt
    @PutMapping(value = "/home/{offset}/{limit}")
    public R hello(@RequestBody BookDto dto,@PathVariable("offset") Integer offset,@PathVariable("limit") Integer limit) {
        System.out.println("***************:"+ JSONObject.toJSONString(dto));
        long start = System.currentTimeMillis();
        String str =  homeFeignClient.helloConsumer();
        System.out.println(str);
        String str2 =  home2FeignClient.helloConsumer();
        System.out.println(str2);
        long end = System.currentTimeMillis();
        String time = "总调用时间是：" + (end - start) + "毫秒";
        System.out.println(time);
        return homeFeignClient.itemList(offset,limit);
    }

    @Decrypt
    @Encrypt
    @PutMapping(value = "/home/abc")
    public R hello(@RequestBody Query query) {
        System.out.println("***************:"+ JSONObject.toJSONString(query));
        long start = System.currentTimeMillis();
        String str =  homeFeignClient.helloConsumer();
        System.out.println(str);
        String str2 =  home2FeignClient.helloConsumer();
        System.out.println(str2);
        long end = System.currentTimeMillis();
        String time = "总调用时间是：" + (end - start) + "毫秒";
        System.out.println(time);
        return homeFeignClient.itemList(1,10);
    }

}
