package com.maemresen.city.list.rest.controller;

import com.maemresen.city.list.domain.service.CityService;
import com.maemresen.city.list.domain.service.model.CityResponseDto;
import com.maemresen.city.list.rest.config.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "City Controller", description = "City related operations like listing, editing etc.")
@RequiredArgsConstructor
@RestController
@RequestMapping("city")
public class CityController {

	private final CityService cityService;

	@Operation(summary = "List cities")
	@GetMapping
	public GenericResponse<Page<CityResponseDto>> findAll(Pageable pageable, @RequestParam Map<String, String> reqestParamMap) {
		return GenericResponse.ok(cityService.findAll(pageable, reqestParamMap));
	}
}
