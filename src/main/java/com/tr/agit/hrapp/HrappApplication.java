package com.tr.agit.hrapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class HrappApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrappApplication.class, args);
	}

}
