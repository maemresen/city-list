package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.model.FileDto;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public interface FileService {

	@Retryable(
		retryFor = ServiceException.class,
		maxAttemptsExpression = "${app.batch.csv-city-import.photo-download-retry.maxAttempts}",
		backoff = @Backoff(delayExpression = "${app.batch.csv-city-import.photo-download-retry.maxDelay}")
	)
	FileDto downloadFile(String url) throws ServiceException;
}
