package com.example.tlyansupport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tlyansupport.feign.SupportFeignService;


@RestController
public class IndexController {
	
	@Autowired
	SupportFeignService supportFeignService;
	
	@GetMapping("/index")
	public String index() {
		return "tl-yan-support up";
	}
	
	@GetMapping("/index2")
	public String index2() {
		List<MessageSendVO> messageSendVOS = new ArrayList<MessageSendVO>();
		return supportFeignService.sendMessage(messageSendVOS);
	}
	
	

}
