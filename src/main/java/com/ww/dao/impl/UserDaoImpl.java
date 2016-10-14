package com.ww.dao.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.ww.dao.UserDao;
import com.ww.vo.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private StringRedisTemplate  redisTemplate;

	@Override
	public void saveUser(final User user) {
		// TODO Auto-generated method stub
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(user.getCode()),
						redisTemplate.getStringSerializer().serialize(user.getName()));
				return null;
			}
		});
	}

}
