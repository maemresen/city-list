package com.maemresen.city.list.rest.config;

import com.maemresen.city.list.rest.controller.AppController;
import org.apache.commons.collections4.CollectionUtils;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig(List<AppController> appControllers) {
		CollectionUtils.emptyIfNull(appControllers).stream()
			.map(AppController::getClass)
			.forEach(super::register);
	}
}
