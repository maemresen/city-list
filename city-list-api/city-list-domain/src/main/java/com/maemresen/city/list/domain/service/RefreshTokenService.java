package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.entity.RefreshToken;
import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.exception.ServiceException;

import java.util.UUID;

/**
 * Refresh Token related operations
 */
public interface RefreshTokenService {

	/**
	 * To check and get existing token
	 * @param token token will be queried
	 * @return found token entity
	 * @throws ServiceException something went wrong or token not found
	 */
	RefreshToken findExistingByToken(UUID token) throws ServiceException;

	/**
	 * To create a new refresh token
	 * @param user the user that refresh token will be created for
	 * @return created refresh token entity
	 */
	RefreshToken createRefreshToken(User user) throws ServiceException;

	/**
	 * To verify expiration of JWT token
	 * @param token token that expiration checked
	 * @throws ServiceException something went wrong or token expired
	 */
	void verifyExpiration(RefreshToken token) throws ServiceException;
}
