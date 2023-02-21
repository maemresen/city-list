package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import com.maemresen.city.list.domain.service.model.dto.SignInRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenRefreshRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenResponseDto;

public interface AuthService {
	TokenResponseDto signIn(SignInRequestDto signInRequestDto) throws ServiceException;

	TokenResponseDto getNewJwtWithRefreshToken(TokenRefreshRequestDto tokenRefreshRequestDto) throws ServiceException;
}
