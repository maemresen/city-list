package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.model.CityResponseDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.InputStream;

public interface CityService {
	CityCreateResponseDto create(CityCreateRequestDto cityCreateDto) throws ServiceException;

    Page<CityResponseDto> findAll(Pageable pageable);

    void importCitesFromCsv(InputStream citiesCsvInputStream) throws ServiceException;
}