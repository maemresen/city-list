package com.maemresen.city.list.domain.exception.business.city;

import com.maemresen.city.list.domain.exception.ExceptionType;
import com.maemresen.city.list.domain.exception.ServiceException;

public class InvalidCityNameException extends ServiceException {

	public InvalidCityNameException() {
		super("Invalid City Name", ExceptionType.INVALID_CITY_NAME);
	}
}
