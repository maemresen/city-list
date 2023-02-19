package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateResponseDto;

import java.io.InputStream;

public interface CityService {
	CityCreateResponseDto create(CityCreateRequestDto cityCreateDto) throws ServiceException;

	void importCitesFromCsv(InputStream citiesCsvInputStream) throws ServiceException;
}
