package com.maemresen.city.list.domain.error.code;

import org.springframework.http.HttpStatus;

import java.util.Map;

public interface ServiceError {
	Map<Class<? extends ServiceError>, Integer> SERVICE_ERROR_INDEX_MAP = Map.ofEntries(
		Map.entry(CommonServiceError.class, 0),
		Map.entry(FileError.class, 1),
		Map.entry(CityError.class, 2)
	);

	default String getCode() {
		final int errorCodeIndex = SERVICE_ERROR_INDEX_MAP.get(getClass());
		return String.format("E%02X-%03x", errorCodeIndex, getCodeNumber());
	}

	int getCodeNumber();

	HttpStatus getHttpStatus();
}
