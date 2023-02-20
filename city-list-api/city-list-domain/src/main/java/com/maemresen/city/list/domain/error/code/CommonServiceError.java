package com.maemresen.city.list.domain.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CommonServiceError implements ServiceError {
	UN_EXPECTED_ERROR(1, HttpStatus.INTERNAL_SERVER_ERROR),
	SERVICE_ERROR(2, HttpStatus.INTERNAL_SERVER_ERROR),
	BAD_REQUEST(3, HttpStatus.BAD_REQUEST),
	UN_AUTHORIZED(4, HttpStatus.UNAUTHORIZED);

	private final int codeNumber;
	private final HttpStatus httpStatus;

	public static CommonServiceError getByHttpStatusCode(int httpStatusCode) {
		return Arrays.stream(values())
			.filter(x -> x.getHttpStatus().value() == httpStatusCode)
			.findFirst()
			.orElse(UN_EXPECTED_ERROR);
	}
}
