package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.service.model.dto.LoginRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenResponseDto;

public interface AuthService {
	TokenResponseDto login(LoginRequestDto loginRequestDto);
}
