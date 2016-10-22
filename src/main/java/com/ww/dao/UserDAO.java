package com.ww.dao;

import com.ww.vo.User;

public interface UserDAO {

	User findByName(String name);

	int insert(String name, Integer age);
}
