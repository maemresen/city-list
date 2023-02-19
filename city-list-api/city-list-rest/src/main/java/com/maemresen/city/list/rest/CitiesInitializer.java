package com.maemresen.city.list.rest;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@RequiredArgsConstructor
@Slf4j
@Component
public class CitiesInitializer implements CommandLineRunner {

	private final CityService cityService;

	@Override
	public void run(String... args) throws ServiceException {

		try (InputStream citiesInputStream = getClass().getClassLoader().getResourceAsStream("data/cities.csv")) {
			cityService.importCitesFromCsv(citiesInputStream);
		} catch (Exception exception) {
			throw new ServiceException("Could not parse initial data.csv", exception);
		}
	}
}
