package com.maemresen.city.list.rest;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.CityService;
import com.maemresen.city.list.domain.service.model.FileDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Slf4j
@Component
public class CitiesInitializer implements CommandLineRunner {

	private static final int CSV_ID_INDEX = 0;
	private static final int CSV_NAME_INDEX = 1;
	private static final int CSV_PHOTO_URL_INDEX = 2;

	private final CityService cityService;

	@Override
	public void run(String... args) throws ServiceException {

		try (InputStream citiesInputStream = getClass().getClassLoader().getResourceAsStream("data/cities.csv")) {
			if (citiesInputStream == null) {
				throw new ServiceException("Failed to initiate input stream");
			}

			try (InputStreamReader citiesInputStreamReader = new InputStreamReader(citiesInputStream);
				 BufferedReader citiesBufferedStream = new BufferedReader(citiesInputStreamReader)) {
				parseFile(citiesBufferedStream);
			}
		} catch (Exception exception) {
			throw new ServiceException("Could not parse initial data.csv", exception);
		}
	}

	private void parseFile(BufferedReader bufferedReader) throws IOException {
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			try {
				CityCreateResponseDto createdCity = createCity(line);
				log.debug("'{}' parsed and saved successfully as {}.", line, createdCity);
			} catch (Exception exception) {
				log.warn("{} could not be parsed - Jumping to the next line.", line, exception);
			}
		}
	}

	private CityCreateResponseDto createCity(String line) throws ServiceException {
		if (StringUtils.isBlank(line)) {
			throw new ServiceException("Line is empty.");
		}

		String[] lineParts = line.split(",");
		if (ArrayUtils.isEmpty(lineParts) || lineParts.length != 3) {
			throw new ServiceException("Some parts missing");
		}

		long id = Long.parseLong(lineParts[CSV_ID_INDEX]);
		String name = lineParts[CSV_NAME_INDEX];
		if (StringUtils.isBlank(name)) {
			throw new ServiceException("City name is empty.");
		}
		String photoUrl = lineParts[CSV_PHOTO_URL_INDEX];

		CityCreateRequestDto cityCreateRequestDto = CityCreateRequestDto.builder()
			.id(id)
			.name(name)
			.photoFile(new FileDto())
			.build();
		return cityService.create(cityCreateRequestDto);
	}
}
