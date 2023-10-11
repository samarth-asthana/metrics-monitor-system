package com.metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@GetMapping("/check")
	public String getMessage(){
		return "Working.....!";
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}