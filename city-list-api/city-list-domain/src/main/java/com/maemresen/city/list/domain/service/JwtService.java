package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.exception.ServiceException;

import java.util.UUID;

/**
 * JWT Related operations
 */
public interface JwtService {

	/**
	 * To generate JWT
	 *
	 * @param username     username of the auth user
	 * @param userUuid     uuid of the auth user
	 * @param userRoleName role of the auth user
	 * @return JWT
	 */
	String generateJwtToken(String username, UUID userUuid, String userRoleName);

	/**
	 * To get username info for the using from existing JWT.
	 *
	 * @param token JWT
	 * @return username extracted from JWT
	 */
	String getUsernameFromJwtToken(String token);

	/**
	 * To validate input JWT. If the token is not valid exception will be thrown
	 *
	 * @param jwt token requested to be validated
	 */
	void validateJwtToken(String jwt) throws ServiceException;
}
