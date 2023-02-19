package com.maemresen.city.list.rest.controller;

import com.maemresen.city.list.domain.service.CityService;
import com.maemresen.city.list.domain.service.model.CityResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("city")
public class CityController {

	private final CityService cityService;

	@GetMapping
	public Page<CityResponseDto> findAll(Pageable pageable){
		return cityService.findAll(pageable);
	}
}
