package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.MockConstants;
import com.maemresen.city.list.domain.entity.File;
import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.service.impl.FileServiceImpl;
import com.maemresen.city.list.domain.service.mapper.FileMapper;
import com.maemresen.city.list.domain.service.model.dto.FileDto;
import com.maemresen.city.list.domain.service.model.prop.FileServerProps;
import com.maemresen.city.list.domain.service.repository.FileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = FileServiceImpl.class)
class FileServiceTest {

	@MockBean
	private FileServerProps fileServerProps;

	@MockBean
	private FileRepository fileRepository;

	@MockBean
	private IOService ioService;

	@MockBean
	private FileMapper fileMapper;

	@Autowired
	private FileServiceImpl fileServiceImpl;

	// findEntityByUuid method tests

	@Test
	void findEntityByUuid_withExistingUuid() {
		final UUID mockUuid = MockConstants.MOCK_UUID;
		final File mockFile = new File();
		mockFile.setUuid(mockUuid);
		Mockito.when(fileRepository.findByUuid(mockUuid)).thenReturn(Optional.of(mockFile));

		Optional<File> optionalFile = Assertions.assertDoesNotThrow(() -> fileServiceImpl.findEntityByUuid(mockUuid));

		Assertions.assertTrue(optionalFile.isPresent());
		Assertions.assertEquals(mockUuid, optionalFile.get().getUuid());
	}

	@Test
	void findEntityByUuid_withNonExistingUuid() {
		final UUID mockUuid = MockConstants.MOCK_UUID;

		Mockito.when(fileRepository.findByUuid(mockUuid)).thenReturn(Optional.empty());

		Optional<File> optionalFile = Assertions.assertDoesNotThrow(() -> fileServiceImpl.findEntityByUuid(mockUuid));

		Assertions.assertTrue(optionalFile.isEmpty());
	}

	@Test
	void findEntityByUuid_withNullUuid() {
		Assertions.assertThrows(ServiceException.class, () -> fileServiceImpl.findEntityByUuid(null));
	}

	// saveEntity method tests

	@Test
	void saveEntity_withFile() {
		final long mockId = 1;

		final File mockFileBeforeSave = new File();

		final File mockFileAfterSave = new File();
		mockFileAfterSave.setId(mockId);

		Mockito.when(fileRepository.save(mockFileBeforeSave)).thenReturn(mockFileAfterSave);

		File file = Assertions.assertDoesNotThrow(() -> fileServiceImpl.saveEntity(mockFileBeforeSave));

		Assertions.assertNotNull(file);
		Assertions.assertEquals(mockId, file.getId());
	}

	@Test
	void saveEntity_withNullFile() {
		Assertions.assertThrows(ServiceException.class, () -> fileServiceImpl.saveEntity(null));
	}

	// downloadFile method tests

	@Test
	void downloadFile_happyPath() throws ServiceException {
		final String mockUrl = "mock-url";
		final byte[] mockBytes = new byte[0];
		final UUID mockUuid = MockConstants.MOCK_UUID;
		final File mockFile = new File();
		mockFile.setUuid(mockUuid);

		final FileDto mockFileDto = new FileDto();
		mockFileDto.setUuid(mockUuid);

		Mockito.when(ioService.downloadAndGetBytes(mockUrl)).thenReturn(mockBytes);
		Mockito.when(ioService.writeBytesToPath(Mockito.any(), Mockito.any())).thenReturn(true);
		Mockito.when(fileRepository.save(Mockito.any())).thenReturn(mockFile);
		Mockito.when(fileMapper.mapToFileDto(mockFile)).thenReturn(mockFileDto);

		FileDto fileDto = Assertions.assertDoesNotThrow(() -> fileServiceImpl.downloadFile(mockUrl));

		Assertions.assertNotNull(fileDto);
		Assertions.assertEquals(mockUuid, fileDto.getUuid());
	}

	@Test
	void downloadFile_downloadFailed() throws ServiceException {
		final String mockUrl = "mock-url";
		final ServiceException mockException = new ServiceException("");

		Mockito.when(ioService.downloadAndGetBytes(mockUrl)).thenThrow(mockException);
		Assertions.assertThrows(mockException.getClass(), () -> fileServiceImpl.downloadFile(mockUrl));
	}

	@Test
	void downloadFile_writingDownloadFileFailed() throws ServiceException {
		final String mockUrl = "mock-url";
		final byte[] mockBytes = new byte[0];

		Mockito.when(ioService.downloadAndGetBytes(mockUrl)).thenReturn(mockBytes);
		Mockito.when(ioService.writeBytesToPath(Mockito.any(), Mockito.any())).thenThrow(ServiceException.class);

		Assertions.assertThrows(ServiceException.class, () -> fileServiceImpl.downloadFile(mockUrl));
	}

}

