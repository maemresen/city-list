package com.maemresen.city.list.domain.service.model.create.city;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityCreateRequestDto {
	private Long id;
	private String name;
	private UUID photoFileUuid;
}
