package com.example.tlyanencrypt.controller;

import com.cxytiandi.encrypt.springboot.annotation.Decrypt;
import com.cxytiandi.encrypt.springboot.annotation.Encrypt;
import com.example.tlyanencrypt.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController{

	@Encrypt
	@GetMapping("/encryptEntity")
	public UserDto encryptEntity() {
		UserDto dto = new UserDto();
		dto.setId(1);
		dto.setName("加密对象");
		return dto;
	}

//	@Encrypt
//	@Decrypt
	@PostMapping("/save")
	public UserDto save(@RequestBody UserDto dto) {
		System.out.println(dto.getId() + "\t" + dto.getName());
		System.out.println(dto.getCreateTime());
		return dto;
	}

}