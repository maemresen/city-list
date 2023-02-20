package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import com.maemresen.city.list.domain.service.model.dto.LoginRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenResponseDto;

public interface AuthService {
	TokenResponseDto login(LoginRequestDto loginRequestDto) throws ServiceException;
}
