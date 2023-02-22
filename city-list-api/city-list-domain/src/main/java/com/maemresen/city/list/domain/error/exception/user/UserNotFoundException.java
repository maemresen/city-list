package com.maemresen.city.list.domain.error.exception.user;

import com.maemresen.city.list.domain.error.code.ServiceError;
import com.maemresen.city.list.domain.error.exception.base.BusinessException;

import java.util.UUID;

public class UserNotFoundException extends BusinessException {
	private static final String MESSAGE = "User not found";
	private static final ServiceError ERROR = ServiceError.USER_NOT_FOUND;

	public UserNotFoundException(String username) {
		super(MESSAGE, ERROR, username);
	}

	public UserNotFoundException(UUID uuid) {
		super(MESSAGE, ERROR, uuid);
	}

	public UserNotFoundException(Long id) {
		super(MESSAGE, ERROR, id);
	}
}
