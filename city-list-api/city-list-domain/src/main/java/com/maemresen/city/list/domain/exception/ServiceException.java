package com.maemresen.city.list.domain.exception;

import lombok.Getter;

@Getter
public class ServiceException extends Exception {
	private final ExceptionType exceptionType;
	private final Object data;

	// ...
	public ServiceException(String message) {
		super(message);
		this.exceptionType = ExceptionType.SERVICE_ERROR;
		this.data = null;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		this.exceptionType = ExceptionType.SERVICE_ERROR;
		this.data = null;
	}

	public ServiceException(Throwable cause) {
		super(cause);
		this.exceptionType = ExceptionType.SERVICE_ERROR;
		this.data = null;
	}

	// ...
	 public ServiceException(String message, Object data) {
		super(message);
		this.data = data;
		 this.exceptionType = ExceptionType.SERVICE_ERROR;
	}

	public ServiceException(Throwable cause, Object data) {
		super(cause);
		this.data = data;
		this.exceptionType = ExceptionType.SERVICE_ERROR;
	}

	public ServiceException(String message, Throwable cause, Object data) {
		super(message, cause);
		this.data = data;
		this.exceptionType = ExceptionType.SERVICE_ERROR;
	}

	// ...
	protected ServiceException(String message, ExceptionType exceptionType) {
		super(message);
		this.exceptionType = exceptionType;
		this.data = null;
	}

	protected ServiceException(String message, ExceptionType exceptionType, Object data) {
		super(message);
		this.exceptionType = exceptionType;
		this.data = data;
	}

	protected ServiceException(Throwable cause, ExceptionType exceptionType) {
		super(cause);
		this.exceptionType = exceptionType;
		this.data = null;
	}

	protected ServiceException(Throwable cause, ExceptionType exceptionType, Object data) {
		super(cause);
		this.exceptionType = exceptionType;
		this.data = data;
	}

	protected ServiceException(String message, Throwable cause, ExceptionType exceptionType) {
		super(message, cause);
		this.exceptionType = exceptionType;
		this.data = null;
	}

	protected ServiceException(String message, Throwable cause, ExceptionType exceptionType, Object data) {
		super(message, cause);
		this.exceptionType = exceptionType;
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("%s[%s]:data=%s",
			exceptionType.getCode(),
			exceptionType,
			data);
	}
}
