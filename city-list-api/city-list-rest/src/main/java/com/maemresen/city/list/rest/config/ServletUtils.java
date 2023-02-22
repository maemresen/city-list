package com.maemresen.city.list.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maemresen.city.list.domain.exception.ExceptionType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Utility methods for Servlet
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServletUtils {

	/**
	 * To set an error response to the Servlet Response object
	 *
	 * @param response      Servlet Response Object
	 * @param exceptionType Type of the exception will be written as response
	 * @throws IOException something went wrong
	 */
	public static void setErrorResponse(HttpServletResponse response, ExceptionType exceptionType) throws IOException {
		response.setStatus(exceptionType.getHttpStatus().value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		OutputStream out = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		var body = GenericResponse.error(exceptionType);
		mapper.writeValue(out, body);
		out.flush();
	}

	/**
	 * To set a success response to the Servlet Response object
	 *
	 * @param response Servlet Response Object
	 * @throws IOException something went wrong
	 */
	public static void setOkResponse(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		OutputStream out = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, GenericResponse.ok());
		out.flush();
	}
}
