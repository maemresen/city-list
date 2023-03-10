package com.maemresen.city.list.domain.util.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Constant definitions that are using as claims in JWT
 */
@Getter
@RequiredArgsConstructor
public enum JwtCustomClaim {

	USER_UUID(true),

	USER_ROLE(true);

	private final boolean required;
}
