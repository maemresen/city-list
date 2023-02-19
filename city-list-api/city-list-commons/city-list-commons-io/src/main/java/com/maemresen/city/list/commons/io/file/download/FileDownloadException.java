package com.maemresen.city.list.commons.io.file.download;

public class FileDownloadException extends Exception {

	public FileDownloadException() {
	}

	public FileDownloadException(String message) {
		super(message);
	}

	public FileDownloadException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileDownloadException(Throwable cause) {
		super(cause);
	}

	public FileDownloadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
