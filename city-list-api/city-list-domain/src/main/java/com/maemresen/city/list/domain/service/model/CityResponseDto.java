package com.maemresen.city.list.domain.service.model;

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
public class CityResponseDto {
	private Long id;
	private String name;
	private UUID photoFileUuid;
}
