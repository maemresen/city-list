package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.MockConstants;
import com.maemresen.city.list.domain.entity.File;
import com.maemresen.city.list.domain.service.impl.FileServiceImpl;
import com.maemresen.city.list.domain.service.mapper.FileMapper;
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
	private FileRepository fileRepository;

	@MockBean
	private FileMapper fileMapper;

	@Autowired
	private FileServiceImpl fileServiceImpl;


	@Test
	void findEntityByUuid_withExistingUuid(){
		final UUID mockUuid = MockConstants.MOCK_UUID;
		final File mockFile = new File();
		mockFile.setUuid(mockUuid);
		Mockito.when(fileRepository.findByUuid(mockUuid)).thenReturn(Optional.of(mockFile));

		Optional<File> optionalFile = fileServiceImpl.findEntityByUuid(mockUuid);

		Assertions.assertTrue(optionalFile.isPresent());
		Assertions.assertEquals(mockUuid, optionalFile.get().getUuid());
	}

	@Test
	void findEntityByUuid_withNonExistingUuid(){
		final UUID mockUuid = MockConstants.MOCK_UUID;

		Mockito.when(fileRepository.findByUuid(mockUuid)).thenReturn(Optional.empty());

		Optional<File> optionalFile = fileServiceImpl.findEntityByUuid(mockUuid);

		Assertions.assertTrue(optionalFile.isEmpty());
	}
}
