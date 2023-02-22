package com.maemresen.city.list.domain.exception.business.auth;

import com.maemresen.city.list.domain.exception.ExceptionType;
import com.maemresen.city.list.domain.exception.business.BusinessException;

public class UnauthorizedUserException extends BusinessException {
	public UnauthorizedUserException(String message) {
		super(message, ExceptionType.UNAUTHORIZED);
	}

	public UnauthorizedUserException(String message, Object data) {
		super(message, ExceptionType.UNAUTHORIZED, data);
	}

	public UnauthorizedUserException(Throwable cause) {
		super(cause, ExceptionType.UNAUTHORIZED);
	}

	public UnauthorizedUserException(Throwable cause, Object data) {
		super(cause, ExceptionType.UNAUTHORIZED, data);
	}

	public UnauthorizedUserException(String message, Throwable cause) {
		super(message, cause, ExceptionType.UNAUTHORIZED);
	}

	public UnauthorizedUserException(String message, Throwable cause, Object data) {
		super(message, cause, ExceptionType.UNAUTHORIZED, data);
	}
}
