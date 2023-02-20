package com.maemresen.city.list.rest.controller;

import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import com.maemresen.city.list.domain.service.UserService;
import com.maemresen.city.list.domain.service.model.dto.UserResponseDto;
import com.maemresen.city.list.rest.config.GenericResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Controller", description = "User related operations.")
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

	private final UserService userService;

	@GetMapping("self")
	public GenericResponse<UserResponseDto> getSelf() throws ServiceException {
		return GenericResponse.ok(userService.getSelf());
	}
}
