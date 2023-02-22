package com.maemresen.city.list.domain.error.exception.auth;

import com.maemresen.city.list.domain.error.code.ServiceError;
import com.maemresen.city.list.domain.error.exception.base.BusinessException;

public class UnauthorizedUserException extends BusinessException {
	public UnauthorizedUserException(String message) {
		super(message, ServiceError.UNAUTHORIZED);
	}

	public UnauthorizedUserException(String message, Object data) {
		super(message, ServiceError.UNAUTHORIZED, data);
	}

	public UnauthorizedUserException(Throwable cause) {
		super(cause, ServiceError.UNAUTHORIZED);
	}

	public UnauthorizedUserException(Throwable cause, Object data) {
		super(cause, ServiceError.UNAUTHORIZED, data);
	}

	public UnauthorizedUserException(String message, Throwable cause) {
		super(message, cause, ServiceError.UNAUTHORIZED);
	}

	public UnauthorizedUserException(String message, Throwable cause, Object data) {
		super(message, cause, ServiceError.UNAUTHORIZED, data);
	}
}
