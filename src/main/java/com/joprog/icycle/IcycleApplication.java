package com.joprog.icycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class IcycleApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcycleApplication.class, args);
	}

}
