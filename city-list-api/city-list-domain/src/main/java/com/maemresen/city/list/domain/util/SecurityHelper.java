package com.maemresen.city.list.domain.util;

import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.service.model.UserDetailsImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityHelper {

	public static UUID getAuthUserUuid(){
		return getAuthUserDetails().getUuid();
	}

	public static User getAuthUser(){
		return getAuthUserDetails().getUser();
	}

	public static UserDetailsImpl getAuthUserDetails(){
		return (UserDetailsImpl) SecurityContextHolder.getContext()
			.getAuthentication()
			.getPrincipal();
	}
}
