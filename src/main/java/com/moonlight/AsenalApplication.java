package com.moonlight;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.moonlight.mybatisplus.mapper")
public class AsenalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsenalApplication.class, args);
	}

}
