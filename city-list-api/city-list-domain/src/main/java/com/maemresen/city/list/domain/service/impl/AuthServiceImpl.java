package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.domain.entity.RefreshToken;
import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.error.exception.auth.BadLoginCredentialsException;
import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import com.maemresen.city.list.domain.service.AuthService;
import com.maemresen.city.list.domain.service.JwtService;
import com.maemresen.city.list.domain.service.RefreshTokenService;
import com.maemresen.city.list.domain.service.model.UserDetailsImpl;
import com.maemresen.city.list.domain.service.model.dto.SignInRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenRefreshRequestDto;
import com.maemresen.city.list.domain.service.model.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final RefreshTokenService refreshTokenService;

	@Override
	public TokenResponseDto signIn(SignInRequestDto signInRequestDto) throws ServiceException {
		try {
			String username = signInRequestDto.getUsername();
			String password = signInRequestDto.getPassword();
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
			String jwt = jwtService.generateJwtToken(username, userPrincipal.getUuid(), userPrincipal.getRoleName().name());

			RefreshToken refreshToken = refreshTokenService.createRefreshToken(userPrincipal.getUser());
			return TokenResponseDto.builder()
				.accessToken(jwt)
				.refreshToken(refreshToken.getToken())
				.build();
		} catch (BadCredentialsException badCredentialsException) {
			throw new BadLoginCredentialsException(badCredentialsException);
		} catch (Exception exception) {
			throw new ServiceException("Error while login user");
		}
	}

	@Override
	public TokenResponseDto getNewJwtWithRefreshToken(TokenRefreshRequestDto tokenRefreshRequestDto) throws ServiceException {
		UUID refreshTokenUuid = UUID.fromString(tokenRefreshRequestDto.getRefreshToken());
		RefreshToken refreshToken = refreshTokenService.findExistingByToken(refreshTokenUuid);

		refreshTokenService.verifyExpiration(refreshToken);
		User user = refreshToken.getUser();
		return TokenResponseDto.builder()
			.accessToken(jwtService.generateJwtToken(user.getUsername(), user.getUuid(), user.getRole().getName().name()))
			.refreshToken(refreshTokenUuid)
			.build();
	}
}
