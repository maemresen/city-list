package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.exception.business.auth.InvalidJwtException;
import com.maemresen.city.list.domain.service.JwtService;
import com.maemresen.city.list.domain.service.model.prop.security.jwt.JwtProps;
import com.maemresen.city.list.domain.util.constants.JwtCustomClaim;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Helper service to deal with some JWT related stuff
 * Like generate, validating token etc.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

	private final JwtProps jwtProps;

	@Override
	public String generateJwtToken(String username, UUID userUuid, String userRoleName) {
		Date issuedAtDate = new Date();

		long expirationTime = issuedAtDate.getTime() + jwtProps.getAccessTokenExpirationMs();
		Date expirationDate = new Date(expirationTime);

		return Jwts.builder()
			.setSubject(username)
			.setIssuedAt(issuedAtDate)
			.setExpiration(expirationDate)
			.signWith(SignatureAlgorithm.HS512, jwtProps.getSecret())
			.claim(JwtCustomClaim.USER_UUID.name(), userUuid.toString())
			.claim(JwtCustomClaim.USER_ROLE.name(), userRoleName)
			.compact();
	}

	@Override
	public String getUsernameFromJwtToken(String token) {
		return Jwts.parser()
			.setSigningKey(jwtProps.getSecret())
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	@Override
	public void validateJwtToken(String jwt) throws ServiceException {
		try {
			Jwts.parser().setSigningKey(jwtProps.getSecret()).parseClaimsJws(jwt);
		} catch (SignatureException e) {
			throw new InvalidJwtException("Invalid JWT signature", e);
		} catch (MalformedJwtException e) {
			throw new InvalidJwtException("Invalid JWT token", e);
		} catch (ExpiredJwtException e) {
			throw new InvalidJwtException("JWT token is expired", e);
		} catch (UnsupportedJwtException e) {
			throw new InvalidJwtException("JWT token is unsupported", e);
		} catch (IllegalArgumentException e) {
			throw new InvalidJwtException("JWT claims string is empty", e);
		}
	}
}
