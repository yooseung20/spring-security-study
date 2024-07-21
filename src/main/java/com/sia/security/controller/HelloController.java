package com.sia.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sia.security.dto.HelloRequest;

@RestController
public class HelloController {

	@GetMapping("/api/v1/hello")
	public String hello(@RequestBody HelloRequest request) {
		return "Hello, Spring Security";
	}
}
