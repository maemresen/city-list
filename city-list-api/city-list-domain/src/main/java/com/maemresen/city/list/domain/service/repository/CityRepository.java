package com.maemresen.city.list.domain.service.repository;

import com.maemresen.city.list.domain.entity.City;
import com.maemresen.city.list.domain.error.exception.ServiceException;

public interface CityRepository {
	City create(City city) throws ServiceException;
}
