package com.leslie.Joblz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class JoblzApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoblzApplication.class, args);
//		byte[] key = new byte[64];
//		SecureRandom random = new SecureRandom();
//		random.nextBytes(key);
//		String base64Key = Base64.getEncoder().encodeToString(key);
//		System.out.println("BASE64 KEY: " + base64Key);
	}


}
