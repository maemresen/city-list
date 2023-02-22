package com.maemresen.city.list.domain.util;

import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.service.model.UserDetailsImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

/**
 * Utility methods for Authentication related operations
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityHelper {

	/**
	 * To get Authenticated user uuid value if exists
	 * @return auth user uuid value
	 */
	public static UUID getAuthUserUuid(){
		return getAuthUserDetails().getUuid();
	}

	/**
	 * To get Authenticated user itself
	 * @return auth user entity
	 */
	public static User getAuthUser(){
		return getAuthUserDetails().getUser();
	}

	/**
	 * Get details info of Authenticated user
	 * @return auth user details
	 */
	public static UserDetailsImpl getAuthUserDetails(){
		return (UserDetailsImpl) SecurityContextHolder.getContext()
			.getAuthentication()
			.getPrincipal();
	}
}
