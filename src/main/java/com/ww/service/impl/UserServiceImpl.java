package com.ww.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ww.annotations.LogAnnotation;
import com.ww.dao.UserDAO;
import com.ww.service.UserService;
import com.ww.vo.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userdao;

	@Override
	@LogAnnotation(moduleName = "user", desc = "根据姓名查询用户", logType = "trace")
	public User getUserByName(String name) {
		return userdao.findByName(name);

	}

}
