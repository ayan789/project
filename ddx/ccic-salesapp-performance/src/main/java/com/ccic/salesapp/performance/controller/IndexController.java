package com.ccic.salesapp.performance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	//健康检查
	@GetMapping("/health")
	public String health() {
		return "performance up";
	}
}
