package com.maemresen.city.list.domain.error.exception.auth;

import com.maemresen.city.list.domain.error.code.AuthError;
import com.maemresen.city.list.domain.error.exception.base.BusinessException;

public class BadLoginCredentialsException extends BusinessException {
	public BadLoginCredentialsException(String message) {
		super(message, AuthError.BAD_CREDENTIALS);
	}

	public BadLoginCredentialsException(String message, Object data) {
		super(message, AuthError.BAD_CREDENTIALS, data);
	}

	public BadLoginCredentialsException(Throwable cause) {
		super(cause, AuthError.BAD_CREDENTIALS);
	}

	public BadLoginCredentialsException(Throwable cause, Object data) {
		super(cause, AuthError.BAD_CREDENTIALS, data);
	}

	public BadLoginCredentialsException(String message, Throwable cause) {
		super(message, cause, AuthError.BAD_CREDENTIALS);
	}

	public BadLoginCredentialsException(String message, Throwable cause, Object data) {
		super(message, cause, AuthError.BAD_CREDENTIALS, data);
	}
}
