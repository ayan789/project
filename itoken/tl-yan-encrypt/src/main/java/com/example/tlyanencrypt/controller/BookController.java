package com.example.tlyanencrypt.controller;

import com.example.tlyanencrypt.dto.BookDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

	@GetMapping("/book/{id}")
	public BookDto book1(@RequestBody BookDto dto,@PathVariable("id") Integer id) {
		System.out.println(dto.getId() + "\t" + dto.getName());
		System.out.println(dto.getCreateTime());
		System.out.println(dto.getPrice());
		System.out.println("GetMapping");
		System.out.println("id:"+id);
		System.out.println("rider:"+dto.getInsure().getRider());
		return dto;
	}

	@PostMapping("/book/{id}")
	public BookDto book2(@RequestBody BookDto dto,@PathVariable("id") Integer id) {
		System.out.println(dto.getId() + "\t" + dto.getName());
		System.out.println(dto.getCreateTime());
		System.out.println(dto.getPrice());
		System.out.println("PostMapping");
		System.out.println("id:"+id);
		System.out.println("rider:"+dto.getInsure().getRider());
		return dto;
	}

	@PutMapping("/book/{id}")
	public BookDto book3(@RequestBody BookDto dto,@PathVariable("id") Integer id) {
		System.out.println(dto.getId() + "\t" + dto.getName());
		System.out.println(dto.getCreateTime());
		System.out.println(dto.getPrice());
		System.out.println("PutMapping");
		System.out.println("id:"+id);
		System.out.println("rider:"+dto.getInsure().getRider());
		return dto;
	}

	@DeleteMapping("/book/{id}")
	public BookDto book4(@RequestBody BookDto dto,@PathVariable("id") Integer id) {
		System.out.println(dto.getId() + "\t" + dto.getName());
		System.out.println(dto.getCreateTime());
		System.out.println(dto.getPrice());
		System.out.println("DeleteMapping");
		System.out.println("id:"+id);
		System.out.println("rider:"+dto.getInsure().getRider());
		return dto;
	}

}
