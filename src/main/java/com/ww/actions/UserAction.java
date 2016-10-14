package com.ww.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ww.service.UserService;
import com.ww.vo.User;

@Controller
@RequestMapping("/user")
public class UserAction {
	@Autowired
	private UserService service;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	@ResponseBody
	public String test() {
		return "ok";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public User saveUser(@RequestBody User user) {
		service.save(user);
		return user;

	}
}
