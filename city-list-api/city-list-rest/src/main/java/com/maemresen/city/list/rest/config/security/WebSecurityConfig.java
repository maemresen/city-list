package com.maemresen.city.list.rest.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

	private final UserDetailsService userDetailsService;
	private final AuthEntryPointJwt authEntryPointJwt;

}
