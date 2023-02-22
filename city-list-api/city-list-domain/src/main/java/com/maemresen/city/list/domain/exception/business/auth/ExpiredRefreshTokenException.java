package com.maemresen.city.list.domain.exception.business.auth;

import com.maemresen.city.list.domain.exception.ExceptionType;
import com.maemresen.city.list.domain.exception.ServiceException;

import java.util.UUID;

public class ExpiredRefreshTokenException extends ServiceException {
	public ExpiredRefreshTokenException(UUID refreshToken) {
		super("Expired Refresh Token", ExceptionType.EXPIRED_REFRESH_TOKEN, refreshToken);
	}
}
