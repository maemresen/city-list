package com.maemresen.city.list.domain.exception.business.auth;

import com.maemresen.city.list.domain.exception.ExceptionType;
import com.maemresen.city.list.domain.exception.business.BusinessException;

public class BadLoginCredentialsException extends BusinessException {
	public BadLoginCredentialsException(String message) {
		super(message, ExceptionType.BAD_CREDENTIALS);
	}

	public BadLoginCredentialsException(String message, Object data) {
		super(message, ExceptionType.BAD_CREDENTIALS, data);
	}

	public BadLoginCredentialsException(Throwable cause) {
		super(cause, ExceptionType.BAD_CREDENTIALS);
	}

	public BadLoginCredentialsException(Throwable cause, Object data) {
		super(cause, ExceptionType.BAD_CREDENTIALS, data);
	}

	public BadLoginCredentialsException(String message, Throwable cause) {
		super(message, cause, ExceptionType.BAD_CREDENTIALS);
	}

	public BadLoginCredentialsException(String message, Throwable cause, Object data) {
		super(message, cause, ExceptionType.BAD_CREDENTIALS, data);
	}
}
