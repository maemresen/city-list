package com.maemresen.city.list.domain.exception.business.city;

import com.maemresen.city.list.domain.exception.ExceptionType;
import com.maemresen.city.list.domain.exception.business.BusinessException;

public class CityNotFoundException extends BusinessException {
	public CityNotFoundException(Long id) {
		super("City not found", ExceptionType.CITY_NOT_FOUND, id);
	}
}
