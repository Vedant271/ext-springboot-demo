package com.example.flipkart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@RequestMapping("/welcome")
	public String welcome() {
		return "Welcome to Spring Boot";
	}
}
