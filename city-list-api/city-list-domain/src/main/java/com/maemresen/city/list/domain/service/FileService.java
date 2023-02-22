package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.service.model.dto.FileDto;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.UUID;

public interface FileService {

	@Retryable(
		retryFor = ServiceException.class,
		maxAttemptsExpression = "${app.batch.csv-city-import.photo-download-retry.maxAttempts}",
		backoff = @Backoff(delayExpression = "${app.batch.csv-city-import.photo-download-retry.maxDelay}")
	)
	FileDto downloadFile(String url) throws ServiceException;

	void writeFile(UUID uuid, OutputStream outputStream) throws ServiceException;

	void storeFile(UUID uuid, MultipartFile multipartFile) throws ServiceException;
}
