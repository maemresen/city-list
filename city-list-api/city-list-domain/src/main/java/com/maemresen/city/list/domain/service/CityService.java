package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.model.CityResponseDto;
import com.maemresen.city.list.domain.service.model.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.CityUpdateRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.InputStream;
import java.util.Map;

public interface CityService {
	CityResponseDto create(CityCreateRequestDto cityCreateDto) throws ServiceException;

	@Transactional
	CityResponseDto update(CityUpdateRequestDto cityUpdateRequestDto) throws ServiceException;

	Page<CityResponseDto> findAll(Pageable pageable, Map<String, String> reqestParamMap);

    void importCitesFromCsv(InputStream citiesCsvInputStream) throws ServiceException;
}
