package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.domain.entity.RefreshToken;
import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.exception.business.user.UserNotFoundException;
import com.maemresen.city.list.domain.service.RefreshTokenService;
import com.maemresen.city.list.domain.service.model.prop.security.jwt.JwtProps;
import com.maemresen.city.list.domain.service.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	private final JwtProps jwtProps;
	private final RefreshTokenRepository refreshTokenRepository;

	@Override
	public RefreshToken findExistingByToken(UUID token) throws ServiceException {
		return refreshTokenRepository.findByToken(token).orElseThrow(() -> new ServiceException("Refresh token not found"));
	}

	@Override
	public RefreshToken createRefreshToken(User user) throws UserNotFoundException {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setUser(user);
		refreshToken.setExpiryDate(LocalDateTime.now().plus(jwtProps.getRefreshTokenExpirationMs(), ChronoUnit.MILLIS));
		refreshToken.setToken(UUID.randomUUID());

		return refreshTokenRepository.save(refreshToken);
	}

	@Override
	public void verifyExpiration(RefreshToken token) throws ServiceException {
		if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
			refreshTokenRepository.delete(token);
			throw new ServiceException("Refresh token expired.");
		}
	}

	@Transactional
	public void deleteByUserId(Long userId) {
		refreshTokenRepository.deleteByUserId(userId);
	}
}
