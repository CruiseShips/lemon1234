package com.lemon1234;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value = "com.lemon1234.mapper")
@EnableScheduling
public class Lemon1234Application {

	public static void main(String[] args) {
		SpringApplication.run(Lemon1234Application.class, args);
	}
}
