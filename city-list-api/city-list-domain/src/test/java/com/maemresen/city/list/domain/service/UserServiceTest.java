package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.exception.business.user.UserNotFoundException;
import com.maemresen.city.list.domain.service.impl.UserServiceImpl;
import com.maemresen.city.list.domain.service.mapper.UserMapper;
import com.maemresen.city.list.domain.service.model.dto.UserResponseDto;
import com.maemresen.city.list.domain.service.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = UserServiceImpl.class)
class UserServiceTest {

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private UserMapper userMapper;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Test
	void getSelf_whenExistingUuid() {
		UUID mockUuid = UUID.randomUUID();
		User mockUser = new User();
		mockUser.setUuid(mockUuid);

		UserResponseDto mockUserResponseDto = new UserResponseDto();
		mockUserResponseDto.setUuid(mockUuid);

		Mockito.when(userRepository.findByUuid(mockUuid)).thenReturn(Optional.of(mockUser));
		Mockito.when(userMapper.mapToUserResponseDto(Mockito.any())).thenReturn(mockUserResponseDto);

		UserResponseDto userResponseDto = Assertions.assertDoesNotThrow(() -> userServiceImpl.getByUuid(mockUuid));
		Assertions.assertNotNull(userResponseDto);
		Assertions.assertEquals(mockUuid, userResponseDto.getUuid());
	}

	@Test
	void getSelf_whenNonExistingUuid() {
		Mockito.when(userRepository.findByUuid(Mockito.any())).thenReturn(Optional.empty());

		Assertions.assertThrows(UserNotFoundException.class, () -> userServiceImpl.getByUuid(null));
	}
}
