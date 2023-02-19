package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.commons.io.csv.read.CsvReadException;
import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.CityService;
import com.maemresen.city.list.domain.service.FileService;
import com.maemresen.city.list.domain.service.mapper.CityMapper;
import com.maemresen.city.list.domain.service.model.CityCsvDto;
import com.maemresen.city.list.domain.service.model.CityResponseDto;
import com.maemresen.city.list.domain.service.model.FileDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateResponseDto;
import com.maemresen.city.list.domain.service.repository.CityRepository;
import com.maemresen.city.list.domain.service.repository.FileRepository;
import com.maemresen.city.list.domain.service.specification.CitySearchSpecification;
import com.maemresen.city.list.domain.util.CitiesCsvReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class CityServiceImpl implements CityService {

	private final CityRepository cityRepository;
	private final FileRepository fileRepository;
	private final CityMapper cityMapper;
	private final CitiesCsvReader citiesCsvReader;
	private final FileService fileService;

	@Transactional
	@Override
	public CityCreateResponseDto create(CityCreateRequestDto cityCreateDto) {
		City city = cityMapper.mapToEntity(cityCreateDto);
		UUID photoFileUuid = cityCreateDto.getPhotoFileUuid();
		fileRepository.findByUuid(photoFileUuid).ifPresent(city::setPhotoFile);
		city = cityRepository.save(city);
		return cityMapper.mapToCreateResponseDto(city);
	}

	@Override
	public Page<CityResponseDto> findAll(Pageable pageable, Map<String, String> reqestParamMap){
		return cityRepository.findAll(new CitySearchSpecification(reqestParamMap), pageable)
			.map(cityMapper::mapToCityDto);
	}

	@Override
	public void importCitesFromCsv(InputStream citiesCsvInputStream) throws ServiceException {
		List<CityCsvDto> citiesData;
		try {
			citiesData = citiesCsvReader.parse(citiesCsvInputStream);
		} catch (CsvReadException e) {
			throw new ServiceException("Error while parsing cities from CSV", e);
		}

		if (CollectionUtils.isEmpty(citiesData)) {
			return;
		}

		for (CityCsvDto cityCsvDto : citiesData) {
			try {
				importCity(cityCsvDto);
				log.debug("Successfully download photo of city with id {} and name {} from {}",
					cityCsvDto.getId(),
					cityCsvDto.getName(),
					cityCsvDto.getPhotoUrl());
			} catch (Exception exception) {
				log.error("Failed to create city with id {} and name {} from {}",
					cityCsvDto.getId(),
					cityCsvDto.getName(),
					cityCsvDto.getPhotoUrl(),
					exception);
			}
		}
	}

	private void importCity(CityCsvDto cityCsvDto) {

		Long id = cityCsvDto.getId();
		String name = cityCsvDto.getName();
		String photoUrl = cityCsvDto.getPhotoUrl();

		CityCreateRequestDto cityCreateRequestDto = new CityCreateRequestDto();
		cityCreateRequestDto.setId(id);
		cityCreateRequestDto.setName(name);

		try {
			FileDto file = fileService.downloadFile(cityCsvDto.getPhotoUrl());
			cityCreateRequestDto.setPhotoFileUuid(file.getUuid());
		} catch (ServiceException exception) {
			log.error("Could not download photo for city with id {} and name {} from {}",
				id,
				name,
				photoUrl,
				exception);
		}

		create(cityCreateRequestDto);
	}
}
