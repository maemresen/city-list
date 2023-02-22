package com.maemresen.city.list.domain.error.exception.file;

import com.maemresen.city.list.domain.error.code.ServiceError;
import com.maemresen.city.list.domain.error.exception.base.ServiceException;

public class FileIoException extends ServiceException {

	public FileIoException(String message, Throwable cause) {
		super(message, cause, ServiceError.FILE_IO_ERROR);
	}
}
