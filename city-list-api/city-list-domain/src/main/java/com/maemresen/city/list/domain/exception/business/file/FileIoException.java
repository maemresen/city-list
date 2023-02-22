package com.maemresen.city.list.domain.exception.business.file;

import com.maemresen.city.list.domain.exception.ExceptionType;
import com.maemresen.city.list.domain.exception.ServiceException;

public class FileIoException extends ServiceException {

	public FileIoException(String message, Throwable cause) {
		super(message, cause, ExceptionType.FILE_IO_ERROR);
	}
}
