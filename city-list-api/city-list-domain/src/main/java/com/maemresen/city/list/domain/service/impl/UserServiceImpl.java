package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import com.maemresen.city.list.domain.error.exception.user.UserNotFoundException;
import com.maemresen.city.list.domain.service.UserService;
import com.maemresen.city.list.domain.service.mapper.UserMapper;
import com.maemresen.city.list.domain.service.model.dto.UserResponseDto;
import com.maemresen.city.list.domain.service.repository.UserRepository;
import com.maemresen.city.list.domain.util.SecurityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public UserResponseDto getSelf() throws ServiceException {
		UUID authUserUuid = SecurityHelper.getAuthUserUuid();
		return userRepository.findByUuid(authUserUuid).map(userMapper::mapToUserResponseDto).orElseThrow(() -> new UserNotFoundException(authUserUuid));
	}
}
