package com.maemresen.city.list.domain.error.exception.base;


import com.maemresen.city.list.domain.error.code.ServiceError;

public abstract class BusinessException extends ServiceException {

	protected BusinessException(String message, ServiceError serviceError) {
		super(message, serviceError, null);
	}

	protected BusinessException(String message, ServiceError serviceError, Object data) {
		super(message, serviceError, data);
	}

	protected BusinessException(Throwable cause, ServiceError serviceError) {
		super(cause, serviceError, null);
	}

	protected BusinessException(Throwable cause, ServiceError serviceError, Object data) {
		super(cause, serviceError, data);
	}

	protected BusinessException(String message, Throwable cause, ServiceError serviceError) {
		super(message, cause, serviceError, null);
	}

	protected BusinessException(String message, Throwable cause, ServiceError serviceError, Object data) {
		super(message, cause, serviceError, data);
	}
}
