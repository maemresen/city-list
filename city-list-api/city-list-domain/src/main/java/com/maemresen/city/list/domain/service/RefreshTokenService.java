package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.entity.RefreshToken;
import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import com.maemresen.city.list.domain.error.exception.user.UserNotFoundException;

import java.util.UUID;

public interface RefreshTokenService {
	RefreshToken findExistingByToken(UUID token) throws ServiceException;

	RefreshToken createRefreshToken(User user) throws UserNotFoundException;

	void verifyExpiration(RefreshToken token) throws ServiceException;
}
