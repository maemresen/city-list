package com.maemresen.city.list.domain.service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
	private List<CityCommentResponseDto> comments;
}
