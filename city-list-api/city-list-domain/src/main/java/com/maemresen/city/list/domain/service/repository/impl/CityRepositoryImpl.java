package com.maemresen.city.list.domain.service.repository.impl;

import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CityRepositoryImpl implements CityRepository {

	@Override
	public City create(City city) throws ServiceException {
		return city;
	}
}
