package com.maemresen.city.list.domain.service.mapper;


import com.maemresen.city.list.domain.entity.CityComment;
import com.maemresen.city.list.domain.service.model.dto.CityAddCommentRequestDto;
import com.maemresen.city.list.domain.service.model.dto.CityCommentResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
	componentModel = "spring",
	builder = @Builder(disableBuilder = true)
)
public interface CityCommentMapper {
	CityComment mapToEntity(CityAddCommentRequestDto requestDto);

	@Mapping(target = "username", source = "commentUser.username")
	CityCommentResponseDto mapToEntity(CityComment cityComment);
}
