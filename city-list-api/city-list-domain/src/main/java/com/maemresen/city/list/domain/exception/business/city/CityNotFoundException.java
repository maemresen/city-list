package com.maemresen.city.list.domain.exception.business.city;

import com.maemresen.city.list.domain.exception.ExceptionType;
import com.maemresen.city.list.domain.exception.ServiceException;

public class CityNotFoundException extends ServiceException {
	public CityNotFoundException(Long id) {
		super("City not found", ExceptionType.CITY_NOT_FOUND, id);
	}
}
