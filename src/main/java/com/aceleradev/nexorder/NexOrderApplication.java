package com.aceleradev.nexorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class NexOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexOrderApplication.class, args);

		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}
}
