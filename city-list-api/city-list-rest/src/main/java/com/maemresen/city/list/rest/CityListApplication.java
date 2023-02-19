package com.maemresen.city.list.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.maemresen.city.list")
public class CityListApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityListApplication.class, args);
	}
}
