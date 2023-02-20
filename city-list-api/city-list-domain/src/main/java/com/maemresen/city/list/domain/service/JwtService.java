package com.maemresen.city.list.domain.service;

import org.springframework.security.core.Authentication;

public interface JwtService {
	String generateJwtToken(Authentication authentication);

	String getUserNameFromJwtToken(String token);

	void validateJwtToken(String jwt);
}
