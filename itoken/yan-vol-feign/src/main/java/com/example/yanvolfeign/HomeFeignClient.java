package com.example.yanvolfeign;

import com.example.yanvolfeign.util.Query;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("yan-vol-main01-bk01")
@Component
public interface HomeFeignClient {

    @GetMapping("/config")
    String helloConsumer();

    @GetMapping("/yanvolmain01bk01/user/list")
    R userList();

    @GetMapping("/yanvolmain01bk01/item/list")
    R itemList(@RequestParam("offset") Integer offset , @RequestParam("limit") Integer limit );

    @GetMapping("/yanvolmain01bk01/item/list2")
    R itemList(Query query);

}
