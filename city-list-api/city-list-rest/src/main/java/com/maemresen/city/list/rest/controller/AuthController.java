package com.maemresen.city.list.rest.controller;

import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.service.AuthService;
import com.maemresen.city.list.domain.service.model.dto.SignInRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenRefreshRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenResponseDto;
import com.maemresen.city.list.rest.config.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth Controller", description = "Authentication related operations.")
@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

	private final AuthService authService;

	@Operation(summary = "Sign In")
	@PostMapping("sign-in")
	public GenericResponse<TokenResponseDto> signIn(@RequestBody @Valid SignInRequestDto signInRequestDto) throws ServiceException {
		return GenericResponse.ok(authService.signIn(signInRequestDto));
	}

	@Operation(summary = "Re-Create JWT with refresh token")
	@PostMapping("refresh-token")
	public GenericResponse<TokenResponseDto> refreshToken(@RequestBody @Valid TokenRefreshRequestDto tokenRefreshRequestDto) throws ServiceException {
		return GenericResponse.ok(authService.getNewJwtWithRefreshToken(tokenRefreshRequestDto));
	}
}
