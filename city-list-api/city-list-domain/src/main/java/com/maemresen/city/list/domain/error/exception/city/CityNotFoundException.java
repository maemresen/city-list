package com.maemresen.city.list.domain.error.exception.city;

import com.maemresen.city.list.domain.error.code.CityError;
import com.maemresen.city.list.domain.error.exception.base.BusinessException;

public class CityNotFoundException extends BusinessException {
	public CityNotFoundException(Long id) {
		super("City not found", CityError.CITY_NOT_FOUND, id);
	}
}
