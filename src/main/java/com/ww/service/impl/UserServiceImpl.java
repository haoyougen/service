package com.ww.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ww.annotations.LogAnnotation;
import com.ww.dao.UserDAO;
import com.ww.service.UserService;
import com.ww.utils.Constants;
import com.ww.vo.User;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDAO userdao;
	@Autowired
	private KafkaTemplate<String, String> kafkatemplate;

	@Override
	@LogAnnotation(moduleName = "user", desc = "根据姓名查询用户", logType = "trace")
	//@Cacheable(value = Constants.PREFIX_CACHE + "user", key = "#name")
	public User getUserByName(String name) {
		LOGGER.info("你正在访问"+ name);
		kafkatemplate.send("will_topic",name);
		return userdao.findByName(name);

	}

}
