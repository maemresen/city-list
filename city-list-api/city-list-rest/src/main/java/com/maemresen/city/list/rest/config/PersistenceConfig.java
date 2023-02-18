package com.maemresen.city.list.rest.config;

import com.maemresen.city.list.rest.props.DataSourceProps;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class PersistenceConfig {

	private final DataSourceProps dataSourceProps;

	@Bean
	public EntityManager entityManager() {
		return entityManagerFactory().createEntityManager();
	}

	private EntityManagerFactory entityManagerFactory() {
		return Persistence.createEntityManagerFactory("default", Map.ofEntries(
			Map.entry("jakarta.persistence.jdbc.driver", dataSourceProps.getDriver()),
			Map.entry("jakarta.persistence.jdbc.url", dataSourceProps.getJdbcUrl()),
			Map.entry("jakarta.persistence.jdbc.user", dataSourceProps.getUsername()),
			Map.entry("jakarta.persistence.jdbc.password", dataSourceProps.getPassword())
		));
	}
}
