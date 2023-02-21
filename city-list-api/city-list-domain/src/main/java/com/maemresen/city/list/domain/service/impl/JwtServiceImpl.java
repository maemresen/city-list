package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.domain.service.JwtService;
import com.maemresen.city.list.domain.service.UserService;
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
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser()
			.setSigningKey(jwtProps.getSecret())
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	@Override
	public void validateJwtToken(String jwt) {
		try {
			Jwts.parser().setSigningKey(jwtProps.getSecret()).parseClaimsJws(jwt);
		} catch (SignatureException e) {
			log.error("Invalid JWT signature", e);
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token", e);
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired", e);
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported", e);
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty", e);
		}
	}
}
