package com.maemresen.city.list.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maemresen.city.list.domain.error.code.ServiceError;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.OutputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServletUtils {

    public static void setErrorResponse(HttpServletResponse response, ServiceError serviceError) throws IOException {
        response.setStatus(serviceError.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
		var body = GenericResponse.error(serviceError);
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
