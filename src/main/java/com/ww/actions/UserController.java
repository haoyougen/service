package com.ww.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ww.annotations.LoginRequired;
import com.ww.service.UserService;
import com.ww.vo.User;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public User getByName(@PathVariable("name") String name) {
		return service.getUserByName(name);
	}

	@RequestMapping(value = "/test", produces = "application/json;charset=UTF-8")
	public User testUser() {
		User u = new User();
		u.setName("w");
		u.setPassword("good");
		return u;
	}

	@LoginRequired
	@RequestMapping(value = "/login/test", produces = "application/json;charset=UTF-8")
	public User loginUser() {
		User u = new User();
		u.setName("w");
		u.setPassword("good");
		return u;
	}
}
