package com.maemresen.city.list.domain.error.exception.user;

import com.maemresen.city.list.domain.error.code.UserError;
import com.maemresen.city.list.domain.error.exception.base.BusinessException;

import java.util.UUID;

public class UserNotFoundException extends BusinessException {
	public UserNotFoundException(String userName) {
		super("User not found", UserError.USER_NOT_FOUND, userName);
	}

	public UserNotFoundException(UUID uuid) {
		super("User not found", UserError.USER_NOT_FOUND, uuid);
	}
}
