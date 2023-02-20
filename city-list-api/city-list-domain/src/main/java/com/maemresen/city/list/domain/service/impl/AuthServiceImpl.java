package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.domain.service.AuthService;
import com.maemresen.city.list.domain.service.JwtService;
import com.maemresen.city.list.domain.service.model.dto.LoginRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenResponseDto;
import com.maemresen.city.list.domain.service.model.prop.security.jwt.JwtProps;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

	private final JwtProps jwtProps;

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	@Override
	public TokenResponseDto login(LoginRequestDto loginRequestDto) {
		String username = loginRequestDto.getUsername();
		String password = loginRequestDto.getPassword();
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtService.generateJwtToken(authentication);

		return TokenResponseDto.builder()
			.token(jwt)
			.tokenExpirationTime(jwtProps.getExpirationMs())
			.refreshToken(null)
			.refreshTokenExpirationTime(100)
			.build();
	}
}
