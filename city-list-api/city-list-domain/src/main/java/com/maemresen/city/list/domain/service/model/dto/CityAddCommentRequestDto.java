package com.maemresen.city.list.domain.service.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class CityAddCommentRequestDto {

	@NotBlank(message = "Comment should not be empty")
	@Size(min = 1, max = 255)
	private String commentText;
}
