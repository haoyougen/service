package com.ww.actions;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ww.service.UserService;
import com.ww.vo.User;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;

	@RequestMapping("name/{name}")
	public User getByName(@PathParam("name") String name) {
		return service.getUserByName(name);
	}

	@RequestMapping("/")
	public User testUser() {
		User u = new User();
		u.setName("w");
		u.setAge(11);
		return u;
	}
}
