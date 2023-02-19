package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.commons.io.csv.read.CsvReadException;
import com.maemresen.city.list.commons.io.file.download.FileDownloadException;
import com.maemresen.city.list.commons.io.file.download.FileDownloadUtil;
import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.entity.File;
import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.CityService;
import com.maemresen.city.list.domain.service.mapper.CityMapper;
import com.maemresen.city.list.domain.service.model.CityCsvDto;
import com.maemresen.city.list.domain.service.model.FileDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateResponseDto;
import com.maemresen.city.list.domain.service.repository.CityRepository;
import com.maemresen.city.list.domain.util.CitiesCsvReader;
import com.maemresen.city.list.domain.util.EntityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
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
		String photoUrl = cityCsvDto.getPhotoUrl();
		UUID photoFileUuid = EntityUtils.generateUUID();
		String downloadFilePath = String.format("downloads/photo-%s", photoFileUuid.toString());
		FileDto photoFile = null;
		try (FileOutputStream fileOutputStream = new FileOutputStream(downloadFilePath)) {
			byte[] photoBytes = FileDownloadUtil.downloadAndGetBytes(photoUrl);
			fileOutputStream.write(photoBytes);
			photoFile = FileDto.builder()
				.uuid(photoFileUuid)
				.build();
		} catch (Exception exception) {
			log.error("Failed to save photo of city with id {} and name {} from {}",
				cityCsvDto.getId(),
				cityCsvDto.getName(),
				cityCsvDto.getPhotoUrl(),
				exception);

		}

		create(CityCreateRequestDto.builder()
			.id(cityCsvDto.getId())
			.name(cityCsvDto.getName())
			.photoFile(photoFile)
			.build()
		);
	}
}
