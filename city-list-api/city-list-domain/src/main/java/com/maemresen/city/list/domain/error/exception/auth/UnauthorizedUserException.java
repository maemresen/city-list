package com.maemresen.city.list.domain.error.exception.auth;

import com.maemresen.city.list.domain.error.code.AuthError;
import com.maemresen.city.list.domain.error.exception.base.BusinessException;

public class UnauthorizedUserException extends BusinessException {
	public UnauthorizedUserException(String message) {
		super(message, AuthError.UNAUTHORIZED);
	}

	public UnauthorizedUserException(String message, Object data) {
		super(message, AuthError.UNAUTHORIZED, data);
	}

	public UnauthorizedUserException(Throwable cause) {
		super(cause, AuthError.UNAUTHORIZED);
	}

	public UnauthorizedUserException(Throwable cause, Object data) {
		super(cause, AuthError.UNAUTHORIZED, data);
	}

	public UnauthorizedUserException(String message, Throwable cause) {
		super(message, cause, AuthError.UNAUTHORIZED);
	}

	public UnauthorizedUserException(String message, Throwable cause, Object data) {
		super(message, cause, AuthError.UNAUTHORIZED, data);
	}
}
