package com.example.tlyancustomer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlcommoncomponents.utils.ObjectUtils;

@RestController
public class IndexController {
	
	@GetMapping("/index")
	public String index() {
		ObjectUtils objectUtils = new ObjectUtils();
		objectUtils.object2Json();
		return "tl-common-components up";
	}

}
