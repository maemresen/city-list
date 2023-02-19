package com.maemresen.city.list.domain.service.mapper;

import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.service.model.CityResponseDto;
import com.maemresen.city.list.domain.service.model.CityCreateRequestDto;
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
	CityResponseDto mapToCityResponseDto(City city);

}
