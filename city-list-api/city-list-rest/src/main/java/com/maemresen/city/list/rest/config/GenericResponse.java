package com.maemresen.city.list.rest.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.maemresen.city.list.domain.exception.ExceptionType;
import com.maemresen.city.list.domain.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse<T> {
	private long timestamp;
	private String message;
	private T data;

	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String errorCode;

	private static long getNow(){
		return new Timestamp(System.currentTimeMillis()).getTime();
	}

	public static <R> GenericResponse<R> ok(R data) {
		return GenericResponse.<R>builder()
			.timestamp(getNow())
			.message("Success")
			.data(data)
			.build();
	}

	public static GenericResponse<Boolean> ok() {
		return ok(true);
	}

	public static <R> GenericResponse<R> error(ExceptionType exceptionType, R data) {
		return GenericResponse.<R>builder()
			.timestamp(getNow())
			.message("Error")
			.data(data)
			.errorCode(exceptionType.getCode())
			.build();
	}

	public static <R> GenericResponse<R> error(ExceptionType exceptionType) {
		return error(exceptionType, null);
	}

	public static GenericResponse<Object> error(ServiceException serviceException) {
		return error(serviceException.getExceptionType(), serviceException.getData());
	}
}
