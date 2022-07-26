package com.springboot.ot_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class OtApplicatonUsingJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtApplicatonUsingJpaApplication.class, args);


		System.out.println("Done!");

	
	}

}
