package com.maemresen.city.list.domain.service.mapper;

import com.maemresen.city.list.domain.entity.File;
import com.maemresen.city.list.domain.service.model.FileDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {
	File mapToEntity(FileDto fileDto);
}
