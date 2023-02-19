package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.create.city.CityCreateResponseDto;

public interface CityService {
	CityCreateResponseDto create(CityCreateRequestDto cityCreateDto) throws ServiceException;
}
