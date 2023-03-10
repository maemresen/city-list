package com.maemresen.city.list.domain.service.mapper;


import com.maemresen.city.list.domain.entity.CityComment;
import com.maemresen.city.list.domain.service.model.dto.CityAddCommentRequestDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
	componentModel = "spring",
	builder = @Builder(disableBuilder = true)
)
public interface CityCommentMapper {
	CityComment mapToEntity(CityAddCommentRequestDto requestDto);
}
