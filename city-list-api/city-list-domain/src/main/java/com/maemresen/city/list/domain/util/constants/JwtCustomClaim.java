package com.maemresen.city.list.domain.util.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtCustomClaim {
	USER_UUID(true);

	private final boolean required;
}
