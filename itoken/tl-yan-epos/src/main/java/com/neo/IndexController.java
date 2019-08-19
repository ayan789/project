package com.neo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/healthCheck")
	public String ok(){
		return "up";
	}
	
	@GetMapping(value="/aa")
	public String aa(){
		userService.aa();
		return "aa";
	}
}
