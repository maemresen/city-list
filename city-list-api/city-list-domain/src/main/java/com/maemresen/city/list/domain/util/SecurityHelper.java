package com.maemresen.city.list.domain.util;

import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.service.model.UserDetailsImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityHelper {


	public UUID getAuthUserUuid(){
		return getAuthUserDetails().getUuid();
	}

	public User getAuthUser(){
		return getAuthUserDetails().getUser();
	}

	public UserDetailsImpl getAuthUserDetails(){
		return (UserDetailsImpl) SecurityContextHolder.getContext()
			.getAuthentication()
			.getDetails();
	}
}
