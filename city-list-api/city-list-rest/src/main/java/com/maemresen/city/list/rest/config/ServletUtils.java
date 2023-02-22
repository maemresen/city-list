package com.maemresen.city.list.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maemresen.city.list.domain.exception.ExceptionType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.OutputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServletUtils {

    public static void setErrorResponse(HttpServletResponse response, ExceptionType exceptionType) throws IOException {
        response.setStatus(exceptionType.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
		var body = GenericResponse.error(exceptionType);
		mapper.writeValue(out, body);
        out.flush();
    }

	public static void setOkResponse(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		OutputStream out = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, GenericResponse.ok());
		out.flush();
	}
}
