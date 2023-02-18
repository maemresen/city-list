package com.maemresen.city.list.domain.service.model.create.city;

import com.maemresen.city.list.domain.service.model.FileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityCreateRequestDto {
	private String name;
	private FileDto photoFile;
}
