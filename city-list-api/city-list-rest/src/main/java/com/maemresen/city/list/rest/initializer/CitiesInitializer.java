package com.maemresen.city.list.rest.initializer;

import com.maemresen.city.list.domain.service.CityService;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CitiesInitializer implements CommandLineRunner {

	private final CityService cityService;

	@Override
	public void run(String... args) throws Exception {
		CityCreateRequestDto cityCreateRequestDto = new CityCreateRequestDto();
		cityCreateRequestDto.setName("City 1");
		cityService.create(cityCreateRequestDto);
	}
}
