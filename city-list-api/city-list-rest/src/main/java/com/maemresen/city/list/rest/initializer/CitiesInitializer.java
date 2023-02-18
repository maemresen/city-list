package com.maemresen.city.list.rest.initializer;

import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.entity.File;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class CitiesInitializer implements CommandLineRunner {

	@PersistenceUnit
	private final EntityManager entityManager;

	@Override
	public void run(String... args) {
			City city = new City();
			city.setName("City 1");
			city.setPhotoFile(new File());
			entityManager.getTransaction().begin();
			entityManager.persist(city);
			entityManager.flush();
			entityManager.getTransaction().commit();
	}
}
