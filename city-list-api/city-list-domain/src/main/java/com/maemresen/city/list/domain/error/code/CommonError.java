package com.maemresen.city.list.domain.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonError implements ServiceError {
	UN_EXPECTED_ERROR(1),
	SERVICE_ERROR(2),
	BAD_REQUEST(3);

	private final int codeNumber;

	@Override
	public String getCodePrefix() {
		return "E00";
	}
}
