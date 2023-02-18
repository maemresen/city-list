package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.service.model.CityCreateRequestDto;

public interface CityService {
	City create(CityCreateRequestDto cityCreateDto);
}
