package com.maemresen.city.list.domain.service.mapper;

import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
	componentModel = "spring",
	uses = FileMapper.class,
	builder = @Builder(disableBuilder = true)
)
public interface CityMapper {
	City mapToEntity(CityCreateRequestDto requestDto);

	@Mapping(target = "photoFileUuid", source = "photoFile.uuid")
	CityCreateResponseDto mapToCreateResponseDto(City city);
}
