package com.maemresen.city.list.rest.config;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.rest.props.DataSourceProps;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.util.Optional;

@RequiredArgsConstructor
@Configuration
public class DataSourceConfig {

	private final DataSourceProps dataSourceProps;

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public DataSource dataSource() throws ServiceException {
		return new HikariDataSource(hikariConfig());
	}

	private HikariConfig hikariConfig() throws ServiceException {
		HikariConfig config = new HikariConfig();

		String jdbcUrl = Optional.of(dataSourceProps)
			.map(DataSourceProps::getJdbcUrl)
			.orElseThrow(() -> new ServiceException("JDBC url not found while configuring datasource"));
		config.setJdbcUrl(jdbcUrl);

		String username = Optional.of(dataSourceProps)
			.map(DataSourceProps::getUsername)
			.orElseThrow(() -> new ServiceException("Username not found while configuring datasource"));
		config.setUsername(username);

		String password = Optional.of(dataSourceProps)
			.map(DataSourceProps::getPassword)

			.orElseThrow(() -> new ServiceException("Username not found while configuring datasource"));
		config.setPassword(password);

		MapUtils.emptyIfNull(dataSourceProps.getProperties()).forEach(config::addDataSourceProperty);
		return config;
	}
}
