package com.maemresen.city.list.rest.config.security.prop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "app.security.jwt")
public class JwtProps {
	private String secret;
	private long expirationMs;
}
