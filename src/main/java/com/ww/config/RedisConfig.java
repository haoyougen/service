package com.ww.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@PropertySource(value = "file:${APP_HOME}/conf/${env}/redis.properties")
public class RedisConfig {
	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.timeout}")
	private int timeout;
	@Value("${spring.redis.node}")
	private String node;

	@Value("${spring.redis.maxWaitMillis}")
	private int maxWaitMillis;
	@Value("${spring.redis.maxTotal}")
	private int maxTotal;
	@Value("${spring.redis.minIdle}")
	private int minIdle;
	@Value("${spring.redis.maxIdle}")
	private int maxIdle;

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		RedisClusterConfiguration redisClusterConfig = redisClusterConfiguration();

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxIdle(maxIdle);
		JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfig, poolConfig);
		return factory;
	}

	@Bean
	public RedisClusterConfiguration redisClusterConfiguration() {
		RedisClusterConfiguration config = new RedisClusterConfiguration();
		String[] nodes = node.split(",");
		List<RedisNode> nodeList = new ArrayList<RedisNode>();
		for (String n : nodes) {
			String hostport[] = n.split(":");
			RedisNode rn1 = new RedisNode(hostport[0], Integer.parseInt(hostport[1]));
			nodeList.add(rn1);
		}

		config.setClusterNodes(nodeList);
		return config;

	}

	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		RedisConnectionFactory factory = redisConnectionFactory();
		StringRedisTemplate redistemplate = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		redistemplate.setValueSerializer(jackson2JsonRedisSerializer);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		return redistemplate;
	}

	@Bean
	public CacheManager cacheManager() {
		RedisTemplate redisTemplate = redisTemplate();
		return new RedisCacheManager(redisTemplate);
	}

}
