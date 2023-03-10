package com.maemresen.city.list.commons.io.csv.read;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to parse and get data from a CSV file
 *
 * @param <T> type of the data will be parsed
 */
@Slf4j
@RequiredArgsConstructor
public abstract class CsvReader<T> {

	/**
	 * Parse given CSV file as an input stream
	 *
	 * @param csvInputStream stream that contains CSV file
	 * @return parsed data from CSV
	 * @throws CsvReadException any error occurred while parsing CSV
	 */
	public List<T> parse(InputStream csvInputStream) throws CsvReadException {
		try {
			if (csvInputStream == null) {
				throw new CsvReadException("Failed to initiate input stream");
			}

			try (InputStreamReader citiesInputStreamReader = new InputStreamReader(csvInputStream);
				 BufferedReader citiesBufferedStream = new BufferedReader(citiesInputStreamReader)) {
				return parseFile(citiesBufferedStream);
			}
		} catch (Exception exception) {
			throw new CsvReadException("CSV Parse Error", exception);
		}
	}

	private List<T> parseFile(BufferedReader bufferedReader) throws IOException {
		List<T> data = new ArrayList<>();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			try {
				data.add(getDataFromLine(line));
				log.debug("'{}' line parsed successfully.", line);
			} catch (Exception exception) {
				log.error("'{}' line could not be parsed - Jumping to the next line.", line, exception);
			}
		}

		return data;
	}

	protected abstract T getDataFromLine(String line) throws CsvReadException;
}
