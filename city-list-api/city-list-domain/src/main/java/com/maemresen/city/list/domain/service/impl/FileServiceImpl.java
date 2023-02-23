package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.domain.entity.File;
import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.exception.business.file.FileIoException;
import com.maemresen.city.list.domain.service.FileService;
import com.maemresen.city.list.domain.service.IOService;
import com.maemresen.city.list.domain.service.mapper.FileMapper;
import com.maemresen.city.list.domain.service.model.dto.FileDto;
import com.maemresen.city.list.domain.service.model.prop.FileServerProps;
import com.maemresen.city.list.domain.service.repository.FileRepository;
import com.maemresen.city.list.domain.util.EntityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class FileServiceImpl implements FileService {

	private final FileServerProps fileServerProps;
	private final FileRepository fileRepository;
	private final IOService ioService;
	private final FileMapper fileMapper;

	private String getFullFilePath(UUID uuid) {
		return String.format("%s/%s", fileServerProps.getDownloadPath(), uuid.toString());
	}

	@Override
	public Optional<File> findEntityByUuid(UUID uuid) throws ServiceException {
		if (uuid == null) {
			throw new ServiceException("UUID cannot be null");
		}
		return fileRepository.findByUuid(uuid);
	}

	@Override
	public File saveEntity(File file) throws ServiceException {
		if (file == null) {
			throw new ServiceException("File cannot be null");
		}
		return fileRepository.save(file);
	}

	@Override
	public FileDto downloadFile(String url) throws ServiceException {
		log.debug("Trying to download {}", url);
		UUID fileUuid = EntityUtils.generateUUID();
		String downloadFilePath = this.getFullFilePath(fileUuid);
		byte[] photoBytes = ioService.downloadAndGetBytes(url);
		ioService.writeBytesToPath(photoBytes, downloadFilePath);
		File downloadedFile = fileRepository.save(File.builder()
			.uuid(fileUuid)
			.build());
		return fileMapper.mapToFileDto(downloadedFile);
	}

	@Override
	public void writeFile(UUID uuid, OutputStream outputStream) throws ServiceException {
		String downloadFilePath = this.getFullFilePath(uuid);
		try (InputStream fileInputStream = new FileInputStream(downloadFilePath)) {
			IOUtils.copy(fileInputStream, outputStream);
		} catch (Exception e) {
			throw new FileIoException("Failed to reach file with " + uuid, e);
		}
	}

	@Override
	public boolean storeFile(UUID uuid, MultipartFile multipartFile) throws ServiceException {
		String downloadFilePath = this.getFullFilePath(uuid);
		try (FileOutputStream fileOutputStream = new FileOutputStream(downloadFilePath)) {
			fileOutputStream.write(multipartFile.getBytes());
			return true;
		} catch (Exception e) {
			throw new FileIoException("Failed to store file", e);
		}
	}
}
