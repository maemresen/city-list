package com.maemresen.city.list.rest.initializer;

import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.entity.File;
import com.maemresen.city.list.domain.error.exception.ServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
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

	@PersistenceUnit
	private final EntityManager entityManager;

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
				parseLine(line);
				log.debug("{} parsed and saved successfully.", line);
			} catch (Exception exception) {
				log.warn("{} could not be parsed - Jumping to the next line.", line, exception);
			}
		}
	}

	private void parseLine(String line) throws ServiceException {
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

		City city = new City();
		city.setId(id);
		city.setName(name);
		city.setPhotoFile(new File());
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entityManager.merge(city));
			entityManager.getTransaction().commit();
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
			throw new ServiceException("City could not be saved.", exception);
		}
	}
}
