package com.maemresen.city.list.domain.error.exception;

public class BusinessException extends ServiceException {

	protected BusinessException(String message, Error error) {
		super(message, error, null);
	}
	protected BusinessException(String message, Error error, Object data) {
		super(message, error, data);
	}

	protected BusinessException(Throwable cause, Error error) {
		super(cause, error, null);
	}
	protected BusinessException(Throwable cause, Error error, Object data) {
		super(cause, error, data);
	}

	protected BusinessException(String message, Throwable cause, Error error) {
		super(message, cause, error, null);
	}

	protected BusinessException(String message, Throwable cause, Error error, Object data) {
		super(message, cause, error, data);
	}
}
