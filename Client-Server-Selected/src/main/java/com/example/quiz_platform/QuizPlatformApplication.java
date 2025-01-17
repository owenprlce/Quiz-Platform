package com.example.quiz_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//random commit

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class QuizPlatformApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(QuizPlatformApplication.class, args);
	}

}
