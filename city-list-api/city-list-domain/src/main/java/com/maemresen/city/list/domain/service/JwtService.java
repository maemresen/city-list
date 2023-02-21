package com.maemresen.city.list.domain.service;

import java.util.UUID;

public interface JwtService {
	String generateJwtToken(String username, UUID userUuid, String userRoleName);

	String getUserNameFromJwtToken(String token);

	void validateJwtToken(String jwt);
}
