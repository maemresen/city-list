package com.maemresen.city.list.domain.exception.business.user;

import com.maemresen.city.list.domain.exception.ExceptionType;
import com.maemresen.city.list.domain.exception.business.BusinessException;

import java.util.UUID;

public class UserNotFoundException extends BusinessException {
	private static final String MESSAGE = "User not found";
	private static final ExceptionType ERROR = ExceptionType.USER_NOT_FOUND;

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
