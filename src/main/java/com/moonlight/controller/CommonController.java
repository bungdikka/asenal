package com.moonlight.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moonlight.jpa.entity.User;
import com.moonlight.jpa.repository.UserRepository;
import com.moonlight.util.RedisUtil;

@RestController
public class CommonController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RedisUtil redisUtil;
	
	@RequestMapping("/hello")
	public String helloWorld() {
		Optional<User> user = userRepository.findById(1L);
		logger.info("JPA test:"+user.get().getPhoneNum());
		String redisRt = redisUtil.get("test");
		logger.info("Redis test:"+redisRt);
		return "hello world";
	}
}
