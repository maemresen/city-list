package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.commons.io.file.download.FileDownloadUtil;
import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.service.IOService;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

@Component
public class IOServiceImpl implements IOService {

	@Override
	public byte[] downloadAndGetBytes(String urlString) throws ServiceException {
		try {
			return FileDownloadUtil.downloadAndGetBytes(urlString);
		} catch (Exception e) {
			throw new ServiceException("Error while downloading file", e, urlString);
		}
	}

	@Override
	public boolean writeBytesToPath(byte[] bytes, String filePath) throws ServiceException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
			fileOutputStream.write(bytes);
			return true;
		} catch (Exception e) {
			throw new ServiceException("Error while writing bytes to file", e, filePath);
		}
	}
}
