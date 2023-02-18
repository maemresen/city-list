package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.service.mapper.CityMapper;
import com.maemresen.city.list.domain.service.repository.CityRepository;
import com.maemresen.city.list.domain.service.CityService;
import com.maemresen.city.list.domain.service.model.CityCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

	private final CityRepository cityRepository;
	private final CityMapper cityMapper;

	@Override
	public City create(CityCreateRequestDto cityCreateDto){
		return null;
	}
}
