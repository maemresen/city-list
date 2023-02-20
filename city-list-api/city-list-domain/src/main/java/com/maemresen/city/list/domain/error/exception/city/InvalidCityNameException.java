package com.maemresen.city.list.domain.error.exception.city;

import com.maemresen.city.list.domain.error.code.CityError;
import com.maemresen.city.list.domain.error.exception.base.BusinessException;

public class InvalidCityNameException extends BusinessException {

	public InvalidCityNameException() {
		super("Invalid City Name", CityError.INVALID_CITY_NAME);
	}
}
