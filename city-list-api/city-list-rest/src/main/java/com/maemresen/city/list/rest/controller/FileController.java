package com.maemresen.city.list.rest.controller;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("file")
public class FileController {

	private final FileService fileService;

	@GetMapping(value = "{uuid}")
	public void getFile(@PathVariable("uuid") UUID uuid, HttpServletResponse response) throws ServiceException {
		try {
			fileService.writeFile(uuid, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}
}
