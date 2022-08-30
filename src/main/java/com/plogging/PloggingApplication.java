package com.plogging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PloggingApplication {
	public static void main(String[] args) {
		SpringApplication.run(PloggingApplication.class, args);
	}

}
