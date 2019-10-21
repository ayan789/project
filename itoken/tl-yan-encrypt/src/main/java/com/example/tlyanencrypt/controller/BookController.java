package com.example.tlyanencrypt.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.tlyanencrypt.dto.BookDto;
import com.example.tlyanencrypt.dto.Insure;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class BookController {

	@GetMapping("/book/{id}")
	public BookDto book1(@PathVariable("id") Integer id) {
		BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setName("ABC");
        bookDto.setPrice(199.99);
        bookDto.setCreateTime(new Date());
        Insure insure = new Insure();
        insure.setRider("附加险");
        bookDto.setInsure(insure);
        String abc = JSONObject.toJSONString(bookDto);
        System.out.println(abc);

		System.out.println(bookDto.getId() + "\t" + bookDto.getName());
		System.out.println(bookDto.getCreateTime());
		System.out.println(bookDto.getPrice());
		System.out.println("GetMapping");
		System.out.println("id:"+id);
		System.out.println("rider:"+bookDto.getInsure().getRider());
		return bookDto;
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
