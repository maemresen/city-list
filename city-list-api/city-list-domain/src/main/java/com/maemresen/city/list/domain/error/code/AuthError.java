package com.maemresen.city.list.domain.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthError implements ServiceError {
	BAD_CREDENTIALS(1, HttpStatus.INTERNAL_SERVER_ERROR),
	UNAUTHORIZED(2, HttpStatus.UNAUTHORIZED);

	private final int codeNumber;
	private final HttpStatus httpStatus;

}
