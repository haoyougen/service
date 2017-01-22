package com.ww.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ww.service.impl.PropertyReader;

@RestController
@RequestMapping("/test")
public class Example {
	@Autowired
	private PropertyReader reader;

	@RequestMapping("/")
	String home() {
		System.out.println("some changes111");
		return "Hello World!";
	}

	@RequestMapping("/prop")
	String prop() {

		return reader.brows();
	}

}
