package com.letuscode.springbatchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringbatchdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbatchdemoApplication.class, args);
	}

}
