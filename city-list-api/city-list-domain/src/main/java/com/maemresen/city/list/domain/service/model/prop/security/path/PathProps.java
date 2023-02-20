package com.maemresen.city.list.domain.service.model.prop.security.path;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "app.security.paths")
public class PathProps {
	private PathAllowStatus publicPaths;
}
