package com.maemresen.city.list.domain.util;

import com.maemresen.city.list.commons.io.csv.read.CsvReadException;
import com.maemresen.city.list.commons.io.csv.read.CsvReader;
import com.maemresen.city.list.domain.service.model.CityCsvDto;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CitiesCsvReader extends CsvReader<CityCsvDto> {

	private static final String CSV_SEPARATOR = ",";
	private static final int CSV_ID_INDEX = 0;
	private static final int CSV_NAME_INDEX = 1;
	private static final int CSV_PHOTO_URL_INDEX = 2;

	@Override
	protected CityCsvDto getDataFromLine(String line) throws CsvReadException {
		if (StringUtils.isBlank(line)) {
			throw new CsvReadException("Line is empty.");
		}

		String[] lineParts = line.split(CSV_SEPARATOR);
		if (ArrayUtils.isEmpty(lineParts) || lineParts.length != 3) {
			throw new CsvReadException("Some parts missing");
		}

		long id = Long.parseLong(lineParts[CSV_ID_INDEX]);
		String name = lineParts[CSV_NAME_INDEX];
		if (StringUtils.isBlank(name)) {
			throw new CsvReadException("City name is empty.");
		}
		String photoUrl = lineParts[CSV_PHOTO_URL_INDEX];

		return CityCsvDto.builder()
			.id(id)
			.name(name)
			.photoUrl(photoUrl)
			.build();
	}
}
