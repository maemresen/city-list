package com.maemresen.city.list.rest.props;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.datasource")
public class DataSourceProps {
	private String jdbcUrl;
	private String username;
	private String password;
	private Map<String, String> properties;
}
