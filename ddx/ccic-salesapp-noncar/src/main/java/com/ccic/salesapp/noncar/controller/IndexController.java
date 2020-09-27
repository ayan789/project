package com.ccic.salesapp.noncar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	//健康检查
	@GetMapping("/health")
	public String health() {
		return "nocar up";
	}
}
