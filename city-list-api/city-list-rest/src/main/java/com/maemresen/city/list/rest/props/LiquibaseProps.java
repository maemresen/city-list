package com.maemresen.city.list.rest.props;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.liquibase")
public class LiquibaseProps {
	private String changeLog;
	private String contexts;
}
