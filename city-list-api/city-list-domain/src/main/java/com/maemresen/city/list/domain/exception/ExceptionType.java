package com.maemresen.city.list.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Service Error Definitions.
 * Each Error type has a group and a codeNumber
 * An error represented to the consumer of this API az foolows;
 * group + codeNumber
 * <p>
 * e.g.
 * group=E00, codeNumber=001
 * then error code will be E00-001
 */
@Getter
@RequiredArgsConstructor
public enum ExceptionType {

	// auth
	UN_EXPECTED_ERROR(ExceptionType.COMMON, "001", HttpStatus.INTERNAL_SERVER_ERROR),
	SERVICE_ERROR(ExceptionType.COMMON, "002", HttpStatus.INTERNAL_SERVER_ERROR),
	BAD_REQUEST(ExceptionType.COMMON, "003", HttpStatus.BAD_REQUEST),

	// auth
	BAD_CREDENTIALS(ExceptionType.AUTH, "001", HttpStatus.INTERNAL_SERVER_ERROR),
	UNAUTHORIZED(ExceptionType.AUTH, "002", HttpStatus.UNAUTHORIZED),
	INVALID_JWT(ExceptionType.AUTH, "003", HttpStatus.UNAUTHORIZED),
	EXPIRED_REFRESH_TOKEN(ExceptionType.AUTH, "004", HttpStatus.UNAUTHORIZED),

	// user
	USER_NOT_FOUND(ExceptionType.USER, "001", HttpStatus.INTERNAL_SERVER_ERROR),

	// city
	CITY_NOT_FOUND(ExceptionType.CITY, "001", HttpStatus.INTERNAL_SERVER_ERROR),
	INVALID_CITY_NAME(ExceptionType.CITY, "002", HttpStatus.INTERNAL_SERVER_ERROR),

	// file

	FILE_IO_ERROR(ExceptionType.FILE, "001", HttpStatus.INTERNAL_SERVER_ERROR);

	private static final String COMMON = "E00";
	private static final String AUTH = "E01";
	private static final String USER = "E02";
	private static final String CITY = "E03";
	private static final String FILE = "E04";

	private final String group;
	private final String codeNumber;
	private final HttpStatus httpStatus;

	public String getCode() {
		return String.format("%s-%s", getGroup(), getCodeNumber());
	}
}
