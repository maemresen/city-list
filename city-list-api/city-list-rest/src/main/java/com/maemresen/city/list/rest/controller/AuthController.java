package com.maemresen.city.list.rest.controller;

import com.maemresen.city.list.domain.service.AuthService;
import com.maemresen.city.list.domain.service.model.dto.LoginRequestDto;
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

	@PostMapping("login")
	public GenericResponse<TokenResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
		return GenericResponse.ok(authService.login(loginRequestDto));
	}
}
