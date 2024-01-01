package com.example.adrbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.example.adrbook")
public class AdrBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdrBookApplication.class, args);
	}

}
