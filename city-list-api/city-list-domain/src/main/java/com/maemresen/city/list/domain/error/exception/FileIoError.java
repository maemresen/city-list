package com.maemresen.city.list.domain.error.exception;

import com.maemresen.city.list.domain.error.code.FileError;

public class FileIoError extends ServiceException {

	public FileIoError(String message, Throwable cause) {
		super(message, cause, FileError.FILE_IO_ERROR);
	}
}
