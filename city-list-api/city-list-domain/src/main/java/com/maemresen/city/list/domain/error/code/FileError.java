package com.maemresen.city.list.domain.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FileError implements ServiceError{
	FILE_IO_ERROR(1, HttpStatus.INTERNAL_SERVER_ERROR);

	private final int codeNumber;
	private final HttpStatus httpStatus;

	@Override
	public String getCodePrefix() {
		return "E01";
	}
}
