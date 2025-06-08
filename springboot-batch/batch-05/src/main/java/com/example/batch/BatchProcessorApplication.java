package com.example.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // enables spring boot scheduling support
public class BatchProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchProcessorApplication.class, args);
	}

}
