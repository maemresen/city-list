package com.maemresen.city.list.rest.config.security.jwt;

import com.maemresen.city.list.domain.error.code.ServiceError;
import com.maemresen.city.list.rest.config.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Spring Security - Authentication entry point for JWT Authentication
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		log.error("Unauthorized error", authException);
		ServletUtils.setErrorResponse(response, ServiceError.UNAUTHORIZED);
	}
}
