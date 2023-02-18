package com.maemresen.city.list.domain.service.mapper;

import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.service.model.CityCreateRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

	City mapToEntity(CityCreateRequestDto requestDto);
}
