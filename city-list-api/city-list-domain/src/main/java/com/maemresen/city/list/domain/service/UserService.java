package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.service.model.dto.UserResponseDto;

/**
 * User related operations
 */
public interface UserService {

	/**
	 * To get user entity of the authenticated user
	 *
	 * @return self info of the auth user
	 * @throws ServiceException something went wrong
	 */
	UserResponseDto getSelf() throws ServiceException;

}
