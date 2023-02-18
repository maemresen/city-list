package com.maemresen.city.list.domain.service.repository.impl;

import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class CityRepositoryImpl implements CityRepository {

	@Override
	public City create(City city) throws ServiceException {
		log.info("Creating {}", city);
		return city;
	}
}
