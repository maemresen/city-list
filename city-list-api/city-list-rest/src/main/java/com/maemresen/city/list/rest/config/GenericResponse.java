package com.maemresen.city.list.rest.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.maemresen.city.list.domain.error.code.ServiceError;
import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse<T> {
	private LocalDateTime timestamp;
	private String message;
	private T data;

	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String errorCode;

	public static <R> GenericResponse<R> ok(R data) {
		return GenericResponse.<R>builder()
			.timestamp(LocalDateTime.now())
			.message("Success")
			.data(data)
			.build();
	}

	public static <R> GenericResponse<R> error(ServiceError serviceError, R data) {
		return GenericResponse.<R>builder()
			.timestamp(LocalDateTime.now())
			.message("Error")
			.data(data)
			.errorCode(serviceError.getCode())
			.build();
	}

	public static <R> GenericResponse<R> error(ServiceError serviceError) {
		return error(serviceError, null);
	}

	public static GenericResponse<Object> error(ServiceException serviceException) {
		return error(serviceException.getServiceError(), serviceException.getData());
	}
}
