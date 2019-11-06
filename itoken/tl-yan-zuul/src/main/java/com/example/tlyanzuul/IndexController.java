package com.example.tlyanzuul;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@GetMapping(value="/home")
	public String home(){
		return "tl-yan-zuul up";
	}


}
