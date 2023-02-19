package com.maemresen.city.list.domain.error.code;

import org.springframework.http.HttpStatus;

public interface ServiceError {

	String getCodePrefix();

	int getCodeNumber();

	HttpStatus getHttpStatus();
}
