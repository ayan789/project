package com.example.tlyancustomer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlcommoncomponents.decrypt.HttpResult;
import com.example.tlcommoncomponents.utils.ObjectUtils;

@RestController
public class IndexController {
	
	@GetMapping("/index")
	public String index() {
		ObjectUtils objectUtils = new ObjectUtils();
		objectUtils.object2Json();
		return "tl-common-components up";
	}
	
	@ResponseBody
    @GetMapping("/health")
    public HttpResult health(@RequestBody java.util.List<Aa> aaa) {
    	System.out.println("1111");
    	return HttpResult.success(aaa,1, "OK");
    }

}
