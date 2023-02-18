package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.CityService;
import com.maemresen.city.list.domain.service.mapper.CityMapper;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateResponseDto;
import com.maemresen.city.list.domain.service.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

	private final CityRepository cityRepository;
	private final CityMapper cityMapper;

	@Override
	public CityCreateResponseDto create(CityCreateRequestDto cityCreateDto) throws ServiceException {
		City city = cityMapper.mapToEntity(cityCreateDto);
		city = cityRepository.create(city);
		return cityMapper.mapToCreateResponseDto(city);
	}
}
