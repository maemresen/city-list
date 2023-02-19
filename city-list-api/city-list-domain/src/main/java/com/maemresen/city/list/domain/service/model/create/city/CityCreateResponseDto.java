package com.maemresen.city.list.domain.service.model.create.city;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CityCreateResponseDto {
	private String name;
	private UUID photoFileUuid;
}
