package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import com.maemresen.city.list.domain.service.model.dto.UserResponseDto;

public interface UserService {
	UserResponseDto getSelf() throws ServiceException;

}
