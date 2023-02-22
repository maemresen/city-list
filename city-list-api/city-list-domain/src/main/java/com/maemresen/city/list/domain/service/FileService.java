package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.service.model.dto.FileDto;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.UUID;

/**
 * File related operations, like downloading or writing to FS
 */
public interface FileService {

	/**
	 * To download and write file to the file-system.
	 * <p>
	 * <p>
	 * Sometimes, source may return fail response and say wait a few time then re-send the request.
	 * <p>
	 * To reduce download failures retry mechanism added for this method by using Spring Retryable.
	 *
	 * @param url source of the file requesting to download
	 * @return created File entity
	 * @throws ServiceException something went wrong
	 * @see Retryable
	 */
	@Retryable(
		retryFor = ServiceException.class,
		maxAttemptsExpression = "${app.batch.csv-city-import.photo-download-retry.maxAttempts}",
		backoff = @Backoff(delayExpression = "${app.batch.csv-city-import.photo-download-retry.maxDelay}")
	)
	FileDto downloadFile(String url) throws ServiceException;

	/**
	 * To read file with uuid from the file system and write it to input output stream
	 *
	 * @param uuid         uuid of the file in file system
	 * @param outputStream stream that file will be written
	 * @throws ServiceException something went wrong
	 */
	void writeFile(UUID uuid, OutputStream outputStream) throws ServiceException;

	/**
	 * To store file in filesystem with given uuid
	 *
	 * @param uuid          uuid of the file in the file system
	 * @param multipartFile fill will be stored
	 * @throws ServiceException something went wrong.
	 */
	void storeFile(UUID uuid, MultipartFile multipartFile) throws ServiceException;
}
