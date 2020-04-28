package com.moonlight.util;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * redis工具类
 * 
 * @author sunan
 * @since 2020-04-24
 */
@Component
public class RedisUtil {

	@Autowired
    RedisTemplate<String, Object> redisTemplate;
	

	/*
	 * String命令
	 */
	public String get(String key) {
		Object o = redisTemplate.opsForValue().get(key);
		if(o==null) {
			return "";
		}
		return o.toString();
	}

	public Integer getInt(String key) {
		String value = redisTemplate.opsForValue().get(key).toString();
		if (!StringUtils.isEmpty(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}

	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public void set(String key, String value, long timeout) {
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * 给set命令的过期时间加一个10秒以内的随机毫秒值，避免雪崩
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public void setRandomEx(String key, String value, long timeout) {
		redisTemplate.opsForValue().set(key, value, timeout+new Random().nextInt(10000), TimeUnit.MILLISECONDS);
	}

	public long incrby(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	public long incr(String key) {
		return redisTemplate.opsForValue().increment(key);
	}

	public long decr(String key) {
		return redisTemplate.opsForValue().decrement(key);
	}

	public long decrby(String key, long delta) {
		return redisTemplate.opsForValue().decrement(key, delta);
	}

	/**
	 * List命令
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public long lpush(String key, String value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}

	public Object rpop(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	public boolean zadd(String key, String value, double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}
	
	public Set<ZSetOperations.TypedTuple<Object>> zrevrange(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
	}
}
