package com.maemresen.city.list.rest.controller;

import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import com.maemresen.city.list.domain.service.AuthService;
import com.maemresen.city.list.domain.service.model.dto.SignInRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenRefreshRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenResponseDto;
import com.maemresen.city.list.rest.config.GenericResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("sign-in")
	public GenericResponse<TokenResponseDto> signIn(@RequestBody @Valid SignInRequestDto signInRequestDto) throws ServiceException {
		return GenericResponse.ok(authService.signIn(signInRequestDto));
	}

	@PostMapping("refresh-token")
	public GenericResponse<TokenResponseDto> refreshToken(@RequestBody @Valid TokenRefreshRequestDto tokenRefreshRequestDto) throws ServiceException {
		return GenericResponse.ok(authService.getNewJwtWithRefreshToken(tokenRefreshRequestDto));
	}
}
