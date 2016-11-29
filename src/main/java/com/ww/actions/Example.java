package com.ww.actions;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Example {
	@RequestMapping("/")
	String home() {
		System.out.println("some changes");
		return "Hello World!";
	}

}
