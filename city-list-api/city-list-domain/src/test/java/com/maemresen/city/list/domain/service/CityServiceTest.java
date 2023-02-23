package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.commons.io.csv.read.CsvReadException;
import com.maemresen.city.list.domain.MockConstants;
import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.entity.File;
import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.exception.business.city.CityNotFoundException;
import com.maemresen.city.list.domain.exception.business.city.InvalidCityNameException;
import com.maemresen.city.list.domain.service.impl.CityServiceImpl;
import com.maemresen.city.list.domain.service.mapper.CityMapper;
import com.maemresen.city.list.domain.service.model.dto.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.dto.CityCsvDto;
import com.maemresen.city.list.domain.service.model.dto.CityResponseDto;
import com.maemresen.city.list.domain.service.model.dto.CityUpdateRequestDto;
import com.maemresen.city.list.domain.service.model.dto.FileDto;
import com.maemresen.city.list.domain.service.repository.CityRepository;
import com.maemresen.city.list.domain.util.CitiesCsvReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
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

	@Test
	void update_happyPath() throws ServiceException {

		final String mockCityNameOld = "mock-city-name-old";
		final String mockCityNameNew = "mock-city-name-new";
		final CityUpdateRequestDto mockCityUpdateRequestDto = CityUpdateRequestDto.builder()
			.name(mockCityNameNew)
			.build();

		final City mockCityOld = new City();
		mockCityOld.setName(mockCityNameOld);

		final City mockCityNew = new City();
		mockCityOld.setName(mockCityNameNew);

		final CityResponseDto mockCityResponseDto = CityResponseDto.builder()
			.name(mockCityNameNew)
			.build();

		Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.of(mockCityOld));
		Mockito.when(cityRepository.save(Mockito.any())).thenReturn(mockCityNew);
		Mockito.when(cityMapper.mapToCityResponseDto(Mockito.any())).thenReturn(mockCityResponseDto);

		CityResponseDto response = Assertions.assertDoesNotThrow(() -> cityServiceImpl.update(mockCityUpdateRequestDto));

		Assertions.assertNotNull(response);
		Assertions.assertEquals(mockCityNameNew, response.getName());
	}

	@Test
	void update_withBlankNewName() {

		final CityUpdateRequestDto mockCityUpdateRequestDto = CityUpdateRequestDto.builder()
			.name(null)
			.build();

		Assertions.assertThrows(InvalidCityNameException.class, () -> cityServiceImpl.update(mockCityUpdateRequestDto));
	}

	@Test
	void update_withNonExistCity() {

		final CityUpdateRequestDto mockCityUpdateRequestDto = CityUpdateRequestDto.builder()
			.name("mock")
			.build();

		Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.empty());

		Assertions.assertThrows(CityNotFoundException.class, () -> cityServiceImpl.update(mockCityUpdateRequestDto));
	}

	@Test
	void importCitesFromCsv_happyPath() throws CsvReadException, ServiceException {
		final String mockCityName = "mock-name";
		final String mockCityPhotoUrl = "mock-url";
		final UUID mockCityPhotoUuid = MockConstants.MOCK_UUID;
		final CityCsvDto mockCityCsvDto = CityCsvDto.builder()
			.name(mockCityName)
			.photoUrl(mockCityPhotoUrl)
			.build();
		final FileDto mockFileDto = new FileDto();
		mockFileDto.setUuid(mockCityPhotoUuid);

		Mockito.when(citiesCsvReader.parse(Mockito.any())).thenReturn(List.of(mockCityCsvDto));
		Mockito.when(fileService.downloadFile(Mockito.any())).thenReturn(mockFileDto);

		Assertions.assertDoesNotThrow(() -> cityServiceImpl.importCitesFromCsv(null));

	}

	@Test
	void importCitesFromCsv_csvParseError() throws CsvReadException {

		Mockito.when(citiesCsvReader.parse(Mockito.any())).thenThrow(CsvReadException.class);
		Assertions.assertThrows(ServiceException.class, () -> cityServiceImpl.importCitesFromCsv(null));
	}

	@Test
	void deletePhoto_happyPath() {
		Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.of(new City()));
		Mockito.when(cityRepository.save(Mockito.any())).thenReturn(new City());

		Assertions.assertDoesNotThrow(() -> cityServiceImpl.deletePhoto(null));
	}

	@Test
	void deletePhoto_nonExistingCity() {
		Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.empty());

		Assertions.assertThrows(CityNotFoundException.class, () -> cityServiceImpl.deletePhoto(null));
	}

	@Test
	void updatePhoto_happyPath() throws ServiceException {
		Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.of(new City()));
		Mockito.when(fileService.storeFile(Mockito.any(), Mockito.any())).thenReturn(true);
		Mockito.when(fileService.saveEntity(Mockito.any())).thenReturn(new File());
		Mockito.when(cityRepository.save(Mockito.any())).thenReturn(new City());

		Assertions.assertDoesNotThrow(() -> cityServiceImpl.deletePhoto(null));
	}

	@Test
	void updatePhoto_nonExistingEntity() {
		Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.empty());

		Assertions.assertThrows(CityNotFoundException.class, () -> cityServiceImpl.updatePhoto(null, null));
	}

	@Test
	void updatePhoto_failureOnStoreFile() throws ServiceException {
		Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.of(new City()));
		Mockito.when(fileService.storeFile(Mockito.any(), Mockito.any())).thenThrow(ServiceException.class);

		Assertions.assertThrows(ServiceException.class, () -> cityServiceImpl.updatePhoto(null, null));
	}
}

