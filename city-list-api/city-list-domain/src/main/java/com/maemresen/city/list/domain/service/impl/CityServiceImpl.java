package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.commons.io.csv.read.CsvReadException;
import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.CityService;
import com.maemresen.city.list.domain.service.mapper.CityMapper;
import com.maemresen.city.list.domain.service.model.CityCsvDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateResponseDto;
import com.maemresen.city.list.domain.service.repository.CityRepository;
import com.maemresen.city.list.domain.util.CitiesCsvReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

	private final CityRepository cityRepository;
	private final CityMapper cityMapper;
	private final CitiesCsvReader citiesCsvReader;

	@Transactional
	@Override
	public CityCreateResponseDto create(CityCreateRequestDto cityCreateDto) {
		City city = cityMapper.mapToEntity(cityCreateDto);
		city = cityRepository.save(city);
		return cityMapper.mapToCreateResponseDto(city);
	}

	@Override
	public void importCitesFromCsv(InputStream citiesCsvInputStream) throws ServiceException, CsvReadException {
		List<CityCsvDto> citiesData = citiesCsvReader.parse(citiesCsvInputStream);
		if (CollectionUtils.isEmpty(citiesData)) {
			return;
		}

		for (CityCsvDto cityData : citiesData) {

		}
	}
}
