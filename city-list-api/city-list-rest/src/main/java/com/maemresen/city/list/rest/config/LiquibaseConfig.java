package com.maemresen.city.list.rest.config;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.rest.props.LiquibaseProps;
import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class LiquibaseConfig {

	private final LiquibaseProps liquibaseProps;

	private final DataSource dataSource;

	@Bean
	@ConditionalOnProperty(value = "app.liquibase.enabled", havingValue = "true")
	public SpringLiquibase liquibase() throws ServiceException {
		SpringLiquibase liquibase = new SpringLiquibase();

		String changeLog = Optional.ofNullable(liquibaseProps.getChangeLog())
			.orElseThrow(() -> new ServiceException("liquibase changelog file not found"));
		liquibase.setChangeLog(changeLog);

		String context = Optional.ofNullable(liquibaseProps.getContexts())
			.orElseThrow(() -> new ServiceException("liquibase context not found"));
		liquibase.setContexts(context);

		liquibase.setDataSource(dataSource);
		return liquibase;
	}
}
