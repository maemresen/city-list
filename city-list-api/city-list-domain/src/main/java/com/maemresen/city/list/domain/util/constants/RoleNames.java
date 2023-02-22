package com.maemresen.city.list.domain.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constant string definitions for the roles existing in the system.
 * Needed to provide role based authentication function for methods and APIs @Secured annotation
 *
 * @see org.springframework.security.access.annotation.Secured
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RoleNames {
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_ALLOW_EDIT = "ROLE_ALLOW_EDIT";
	public static final String ROLE_READ_ONLY = "ROLE_READ_ONLY";
}
