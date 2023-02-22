package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.MockConstants;
import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.entity.File;
import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.service.impl.CityServiceImpl;
import com.maemresen.city.list.domain.service.mapper.CityMapper;
import com.maemresen.city.list.domain.service.model.dto.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.dto.CityResponseDto;
import com.maemresen.city.list.domain.service.repository.CityRepository;
import com.maemresen.city.list.domain.util.CitiesCsvReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = CityServiceImpl.class)
class CityServiceTest {

	@MockBean
	private CityRepository cityRepository;

	@MockBean
	private CityMapper cityMapper;

	@MockBean
	private CitiesCsvReader citiesCsvReader;

	@MockBean
	private FileService fileService;


	@Autowired
	private CityServiceImpl cityServiceImpl;

	// findEntityByUuid method tests

	@Test
	void create_withExistingFile() throws ServiceException {
		final UUID mockPhotoFileUuid = MockConstants.MOCK_UUID;
		final String mockCityName = "mock-city";
		final CityCreateRequestDto mockCityCreateRequestDto = CityCreateRequestDto.builder()
			.photoFileUuid(mockPhotoFileUuid)
			.name(mockCityName)
			.build();

		final City mockCity = new City();
		mockCity.setName(mockCityName);

		final File mockFile = new File();
		mockFile.setUuid(mockPhotoFileUuid);

		final City mockCityWithFile = new City();
		mockCity.setName(mockCityName);
		mockCity.setPhotoFile(mockFile);

		final CityResponseDto mockCityResponseDto = CityResponseDto.builder()
			.name(mockCityName)
			.photoFileUuid(mockPhotoFileUuid)
			.build();

		Mockito.when(cityMapper.mapToEntity(Mockito.any())).thenReturn(mockCity);
		Mockito.when(fileService.findEntityByUuid(Mockito.any())).thenReturn(Optional.of(mockFile));
		Mockito.when(cityRepository.save(Mockito.any())).thenReturn(mockCityWithFile);
		Mockito.when(cityMapper.mapToCityResponseDto(Mockito.any())).thenReturn(mockCityResponseDto);

		CityResponseDto response = Assertions.assertDoesNotThrow(() -> cityServiceImpl.create(mockCityCreateRequestDto));

		Assertions.assertNotNull(response);
		Assertions.assertEquals(mockPhotoFileUuid, response.getPhotoFileUuid());
		Assertions.assertEquals(mockCityName, response.getName());
	}

	@Test
	void create_withNonExistingFile() throws ServiceException {
		final UUID mockPhotoFileUuid = MockConstants.MOCK_UUID;
		final String mockCityName = "mock-city";
		final CityCreateRequestDto mockCityCreateRequestDto = CityCreateRequestDto.builder()
			.name(mockCityName)
			.build();

		final City mockCity = new City();
		mockCity.setName(mockCityName);

		final City mockCityWithFile = new City();
		mockCity.setName(mockCityName);

		final CityResponseDto mockCityResponseDto = CityResponseDto.builder()
			.name(mockCityName)
			.build();

		Mockito.when(cityMapper.mapToEntity(Mockito.any())).thenReturn(mockCity);
		Mockito.when(fileService.findEntityByUuid(Mockito.any())).thenReturn(Optional.empty());
		Mockito.when(cityRepository.save(Mockito.any())).thenReturn(mockCityWithFile);
		Mockito.when(cityMapper.mapToCityResponseDto(Mockito.any())).thenReturn(mockCityResponseDto);

		CityResponseDto response = Assertions.assertDoesNotThrow(() -> cityServiceImpl.create(mockCityCreateRequestDto));

		Assertions.assertNotNull(response);
		Assertions.assertNull(response.getPhotoFileUuid());
		Assertions.assertEquals(mockCityName, response.getName());
	}

}

