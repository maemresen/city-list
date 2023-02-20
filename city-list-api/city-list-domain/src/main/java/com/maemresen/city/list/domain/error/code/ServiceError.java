package com.maemresen.city.list.domain.error.code;

import org.springframework.http.HttpStatus;

import java.util.Map;

public interface ServiceError {
	Map<Class<? extends ServiceError>, String> SERVICE_ERROR_INDEX_MAP = Map.ofEntries(
		Map.entry(CommonServiceError.class, "E01"),
		Map.entry(FileError.class, "E02"),
		Map.entry(CityError.class, "E03"),
		Map.entry(UserError.class, "E04"),
		Map.entry(AuthError.class, "E05")
	);

	default String getCode() {
		final String errorCodePrefix = SERVICE_ERROR_INDEX_MAP.get(getClass());
		return String.format("%s-%03x", errorCodePrefix, getCodeNumber());
	}

	int getCodeNumber();

	HttpStatus getHttpStatus();
}
