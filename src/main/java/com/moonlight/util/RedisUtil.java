package com.moonlight.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis工具类
 * 
 * @author sunan
 * @since 2020-04-24
 */
@SuppressWarnings("rawtypes")
@Component
public class RedisUtil {

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}
}
