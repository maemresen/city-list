package com.maemresen.city.list.rest.config;

import com.maemresen.city.list.domain.error.code.CommonServiceError;
import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Object> handleServiceException(Throwable throwable, WebRequest request) {

		if (throwable instanceof ServiceException serviceException) {
			log.error("{} occurred while processing request {} ",
				serviceException,
				request.toString(),
				throwable);
			HttpStatus httpStatus = serviceException.getServiceError().getHttpStatus();
			return new ResponseEntity<>(GenericResponse.error(serviceException), new HttpHeaders(), httpStatus);
		} else {
			log.error("An occurred while processing request {} ",
				request.toString(),
				throwable);
			CommonServiceError error = CommonServiceError.UN_EXPECTED_ERROR;
			return new ResponseEntity<>(GenericResponse.error(error), new HttpHeaders(), error.getHttpStatus());
		}
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
		log.error("An error occurred while processing request {} ", request.toString(), ex);
		CommonServiceError error = CommonServiceError.getByHttpStatusCode(statusCode.value());
		return new ResponseEntity<>(GenericResponse.error(error), new HttpHeaders(), statusCode);
	}
}
