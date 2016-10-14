package com.ww.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ww.dao.UserDao;
import com.ww.service.UserService;
import com.ww.vo.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao dao;

	public void save(User user) {
		// TODO Auto-generated method stub
		dao.saveUser(user);
	}

}
