package com.maemresen.city.list.domain.exception.business;


import com.maemresen.city.list.domain.exception.ExceptionType;
import com.maemresen.city.list.domain.exception.ServiceException;

public abstract class BusinessException extends ServiceException {

	protected BusinessException(String message, ExceptionType exceptionType) {
		super(message, exceptionType, null);
	}

	protected BusinessException(String message, ExceptionType exceptionType, Object data) {
		super(message, exceptionType, data);
	}

	protected BusinessException(Throwable cause, ExceptionType exceptionType) {
		super(cause, exceptionType, null);
	}

	protected BusinessException(Throwable cause, ExceptionType exceptionType, Object data) {
		super(cause, exceptionType, data);
	}

	protected BusinessException(String message, Throwable cause, ExceptionType exceptionType) {
		super(message, cause, exceptionType, null);
	}

	protected BusinessException(String message, Throwable cause, ExceptionType exceptionType, Object data) {
		super(message, cause, exceptionType, data);
	}
}
