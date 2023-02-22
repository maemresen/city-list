package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.service.model.dto.SignInRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenRefreshRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenResponseDto;

/**
 * Authentication related business operations
 */
public interface AuthService {

	/**
	 * Sign In user and return JWT and refresh token generated
	 *
	 * @param signInRequestDto sign in request
	 * @return JWT and refresh token
	 * @throws ServiceException an error while sign-in
	 */
	TokenResponseDto signIn(SignInRequestDto signInRequestDto) throws ServiceException;

	/**
	 * To provide new JWT with using a valid refresh token
	 *
	 * @param tokenRefreshRequestDto refresh token to re-generate JWT
	 * @return re-generated JWT
	 * @throws ServiceException an error while token generation
	 */
	TokenResponseDto getNewJwtWithRefreshToken(TokenRefreshRequestDto tokenRefreshRequestDto) throws ServiceException;
}
