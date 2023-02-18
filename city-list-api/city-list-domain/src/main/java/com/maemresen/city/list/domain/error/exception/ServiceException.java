package com.maemresen.city.list.domain.error.exception;

import com.maemresen.city.list.domain.error.code.CommonError;
import com.maemresen.city.list.domain.error.code.ServiceError;
import lombok.Getter;

@Getter
public class ServiceException extends Exception {
	private final ServiceError serviceError;
	private final Object data;

	// ...
	public ServiceException(String message) {
		super(message);
		this.serviceError = CommonError.SERVICE_ERROR;
		this.data = null;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		this.serviceError = CommonError.SERVICE_ERROR;
		this.data = null;
	}

	public ServiceException(Throwable cause) {
		super(cause);
		this.serviceError = CommonError.SERVICE_ERROR;
		this.data = null;
	}

	// ...
	 public ServiceException(String message, Object data) {
		super(message);
		this.data = data;
		 this.serviceError = CommonError.SERVICE_ERROR;
	}

	public ServiceException(Throwable cause, Object data) {
		super(cause);
		this.data = data;
		this.serviceError = CommonError.SERVICE_ERROR;
	}

	public ServiceException(String message, Throwable cause, Object data) {
		super(message, cause);
		this.data = data;
		this.serviceError = CommonError.SERVICE_ERROR;
	}

	// ...
	protected ServiceException(String message, ServiceError serviceError, Object data) {
		super(message);
		this.serviceError = serviceError;
		this.data = data;
	}

	protected ServiceException(Throwable cause, ServiceError serviceError, Object data) {
		super(cause);
		this.serviceError = serviceError;
		this.data = data;
	}

	protected ServiceException(String message, Throwable cause, ServiceError serviceError, Object data) {
		super(message, cause);
		this.serviceError = serviceError;
		this.data = data;
	}
}