package com.maemresen.city.list.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.maemresen.city.list")
@EnableJpaRepositories(basePackages = "com.maemresen.city.list")
@SpringBootApplication(scanBasePackages = "com.maemresen.city.list")
public class CityListApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityListApplication.class, args);
	}
}
