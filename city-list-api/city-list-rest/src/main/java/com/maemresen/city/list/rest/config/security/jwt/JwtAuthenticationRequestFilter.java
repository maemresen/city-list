package com.maemresen.city.list.rest.config.security.jwt;

import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.service.JwtService;
import com.maemresen.city.list.domain.service.impl.CustomUserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Request filter to check JWT included into HTTP request, validate and authenticate if possible
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationRequestFilter extends OncePerRequestFilter {

	private static final String BEARER_TOKEN_PREFIX = "Bearer ";

	private final JwtService jwtService;
	private final CustomUserDetailsServiceImpl customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
		try {
			this.handleJwt(request);
		} catch (Exception e) {
			log.error("Cannot set user authentication", e);
		}

		filterChain.doFilter(request, response);
	}

	private void handleJwt(HttpServletRequest request) throws ServiceException {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isBlank(authorizationHeader)) {
			return;
		}

		String jwt = authorizationHeader.substring(BEARER_TOKEN_PREFIX.length());
		jwtService.validateJwtToken(jwt);

		String username = jwtService.getUsernameFromJwtToken(jwt);
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
