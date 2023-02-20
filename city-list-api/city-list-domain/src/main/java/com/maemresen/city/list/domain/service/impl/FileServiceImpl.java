package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.commons.io.file.download.FileDownloadUtil;
import com.maemresen.city.list.domain.entity.File;
import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import com.maemresen.city.list.domain.error.exception.file.FileIoException;
import com.maemresen.city.list.domain.service.FileService;
import com.maemresen.city.list.domain.service.mapper.FileMapper;
import com.maemresen.city.list.domain.service.model.dto.FileDto;
import com.maemresen.city.list.domain.service.repository.FileRepository;
import com.maemresen.city.list.domain.util.EntityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class FileServiceImpl implements FileService {

	private final FileRepository fileRepository;
	private final FileMapper fileMapper;

	@Override
	public FileDto downloadFile(String url) throws ServiceException {
		log.debug("Trying to download {}", url);
		UUID fileUuid = EntityUtils.generateUUID();
		String downloadFilePath = String.format("downloads/%s", fileUuid);
		try (FileOutputStream fileOutputStream = new FileOutputStream(downloadFilePath)) {
			byte[] photoBytes = FileDownloadUtil.downloadAndGetBytes(url);
			fileOutputStream.write(photoBytes);
			File downloadedFile = fileRepository.save(File.builder()
				.uuid(fileUuid)
				.build());
			return fileMapper.mapToFileDto(downloadedFile);
		} catch (Exception exception) {
			throw new ServiceException("Error while downloading file from " + url, exception);
		}
	}

	@Override
	public void writeFile(UUID uuid, OutputStream outputStream) throws ServiceException {
		try (InputStream fileInputStream = new FileInputStream("downloads/" + uuid)) {
			IOUtils.copy(fileInputStream, outputStream);
		} catch (Exception e) {
			throw new FileIoException("Failed to reach file with " + uuid, e);
		}
	}
}
