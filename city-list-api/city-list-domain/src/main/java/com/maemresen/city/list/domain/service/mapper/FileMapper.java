package com.maemresen.city.list.domain.service.mapper;

import com.maemresen.city.list.domain.entity.File;
import com.maemresen.city.list.domain.service.model.dto.FileDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
	componentModel = "spring",
	builder = @Builder(disableBuilder = true)
)
public interface FileMapper {
	File mapToEntity(FileDto fileDto);

	FileDto mapToFileDto(File file);
}
