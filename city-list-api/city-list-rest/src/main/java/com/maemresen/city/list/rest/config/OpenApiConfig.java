package com.maemresen.city.list.rest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(
	type = SecuritySchemeType.HTTP,
	name = "JWT",
	scheme = "Bearer",
	bearerFormat = "JWT"
)
@OpenAPIDefinition(
	info = @Info(title = "BE API", version = "v1"),
	security = @SecurityRequirement(name = "JWT")
)
public class OpenApiConfig {
}
