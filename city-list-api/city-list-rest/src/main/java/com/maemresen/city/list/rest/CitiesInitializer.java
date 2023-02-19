package com.maemresen.city.list.rest;

import com.maemresen.city.list.domain.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@RequiredArgsConstructor
@Slf4j
@Component
@Profile("cityInitializer")
public class CitiesInitializer implements CommandLineRunner {

	private final CityService cityService;

	@Override
	public void run(String... args) throws Exception {
		try (InputStream citiesInputStream = getClass().getClassLoader().getResourceAsStream("data/cities.csv")) {
			cityService.importCitesFromCsv(citiesInputStream);
		}
	}
}
