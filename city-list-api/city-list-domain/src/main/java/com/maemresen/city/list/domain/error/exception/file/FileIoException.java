package com.maemresen.city.list.domain.error.exception.file;

import com.maemresen.city.list.domain.error.code.FileError;
import com.maemresen.city.list.domain.error.exception.ServiceException;

public class FileIoException extends ServiceException {

	public FileIoException(String message, Throwable cause) {
		super(message, cause, FileError.FILE_IO_ERROR);
	}
}
