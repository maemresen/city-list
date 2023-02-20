package com.maemresen.city.list.domain.service.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class TokenResponseDto {

	@NotBlank
	private String token;

	@NotNull
	private long tokenExpirationTime;

	@NotBlank
	private String refreshToken;

	@NotNull
	private int refreshTokenExpirationTime;
}
