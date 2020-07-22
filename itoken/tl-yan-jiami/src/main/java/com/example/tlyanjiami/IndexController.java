package com.example.tlyanjiami;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@ResponseBody
    @GetMapping("/health")
    public HttpResult health(@RequestBody java.util.List<Aa> aaa) {
    	System.out.println("1111");
        return HttpResult.success(aaa,1, "OK");
    }
}
