package com.maemresen.city.list.domain.exception.business.auth;

import com.maemresen.city.list.domain.exception.ExceptionType;
import com.maemresen.city.list.domain.exception.ServiceException;

public class InvalidJwtException extends ServiceException {
	public InvalidJwtException(String message) {
		super(message, ExceptionType.INVALID_JWT);
	}

	public InvalidJwtException(String message, Throwable cause) {
		super(message, cause, ExceptionType.INVALID_JWT);
	}
}
