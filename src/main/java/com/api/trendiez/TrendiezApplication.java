package com.api.trendiez;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.security.SecureRandom;
import java.util.Base64;


@SpringBootApplication
public class TrendiezApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrendiezApplication.class, args);
//		SecureRandom random = new SecureRandom();
//		byte[] key = new byte[32]; // 32 bytes = 256 bits
//		random.nextBytes(key);
//		String secret = Base64.getEncoder().encodeToString(key);
//		System.out.println("Secret key: " + secret);
	}

}
