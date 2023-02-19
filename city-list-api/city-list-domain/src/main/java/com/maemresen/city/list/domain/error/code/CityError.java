package com.maemresen.city.list.domain.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CityError implements ServiceError{
	CITY_NOT_FOUND(1, HttpStatus.INTERNAL_SERVER_ERROR),
	INVALID_CITY_NAME(2, HttpStatus.INTERNAL_SERVER_ERROR);

	private final int codeNumber;
	private final HttpStatus httpStatus;

}
