package com.maemresen.city.list.domain.service.mapper;

import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.service.model.dto.UserResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
	componentModel = "spring",
	builder = @Builder(disableBuilder = true)
)
public interface UserMapper {

	@Mapping(target = "role", source = "role.name")
	UserResponseDto mapToUserResponseDto(User user);
}
