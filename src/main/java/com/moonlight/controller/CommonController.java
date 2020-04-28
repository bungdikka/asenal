package com.moonlight.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moonlight.jpa.repository.UserRepository;
import com.moonlight.util.RedisUtil;

@RestController
public class CommonController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepository userRepository;
	
//	@Autowired
//	RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	RedisUtil redisUtil;
	
	@RequestMapping("/hello")
	public Object helloWorld() {
//		Optional<User> user = userRepository.findById(1L);
//		logger.info("JPA test:"+user.get().getPhoneNum());
//		String redisRt = redisUtil.get("test").toString();
//		logger.info("Redis test:"+redisRt);
		Object redisCluster = redisUtil.get("foo");
//		Object redisCluster = redisTemplate.opsForValue().get("foo");
		logger.info("Redis cluster test:"+redisCluster);
		return redisCluster;
	}
}
