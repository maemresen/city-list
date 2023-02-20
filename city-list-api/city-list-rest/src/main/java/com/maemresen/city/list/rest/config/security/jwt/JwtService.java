package com.maemresen.city.list.rest.config.security.jwt;

import com.maemresen.city.list.rest.config.security.prop.JwtProps;
import com.maemresen.city.list.rest.config.security.user.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Helper service to deal with some JWT related stuff
 * Like generate, validating token etc.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtService {

	private final JwtProps jwtProps;

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		String subject = userPrincipal.getUsername();
		Date issuedAtDate = new Date();

		long expirationTime = issuedAtDate.getTime() + jwtProps.getExpirationMs();
		Date expirationDate =new Date(expirationTime);

		return Jwts.builder()
				.setSubject(subject)
				.setIssuedAt(issuedAtDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, jwtProps.getSecret())
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser()
			.setSigningKey(jwtProps.getSecret())
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	public boolean validateJwtToken(String jwt) {
		try {
			Jwts.parser().setSigningKey(jwtProps.getSecret()).parseClaimsJws(jwt);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
}
