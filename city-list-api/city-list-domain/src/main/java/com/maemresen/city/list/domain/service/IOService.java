package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.exception.ServiceException;

/**
 * Helper service for IO related operations
 */
public interface IOService {

	/**
	 * Download file from url
	 *
	 * @param urlString file source
	 * @return bytes of file
	 * @throws ServiceException something went wrong
	 */
	byte[] downloadAndGetBytes(String urlString) throws ServiceException;

	/**
	 * Write bytes to destination path
	 *
	 * @param bytes    bytes
	 * @param filePath file path
	 * @return
	 * @throws ServiceException something went wrong
	 */
	boolean writeBytesToPath(byte[] bytes, String filePath) throws ServiceException;
}
