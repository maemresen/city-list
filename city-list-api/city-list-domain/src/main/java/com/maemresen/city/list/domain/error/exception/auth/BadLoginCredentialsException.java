package com.maemresen.city.list.domain.error.exception.auth;

import com.maemresen.city.list.domain.error.code.ServiceError;
import com.maemresen.city.list.domain.error.exception.base.BusinessException;

public class BadLoginCredentialsException extends BusinessException {
	public BadLoginCredentialsException(String message) {
		super(message, ServiceError.BAD_CREDENTIALS);
	}

	public BadLoginCredentialsException(String message, Object data) {
		super(message, ServiceError.BAD_CREDENTIALS, data);
	}

	public BadLoginCredentialsException(Throwable cause) {
		super(cause, ServiceError.BAD_CREDENTIALS);
	}

	public BadLoginCredentialsException(Throwable cause, Object data) {
		super(cause, ServiceError.BAD_CREDENTIALS, data);
	}

	public BadLoginCredentialsException(String message, Throwable cause) {
		super(message, cause, ServiceError.BAD_CREDENTIALS);
	}

	public BadLoginCredentialsException(String message, Throwable cause, Object data) {
		super(message, cause, ServiceError.BAD_CREDENTIALS, data);
	}
}
