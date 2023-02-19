package com.maemresen.city.list.rest.controller;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import com.maemresen.city.list.domain.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@Tag(name = "File Controller", description = "File related operations like downloading files etc.")
@RequiredArgsConstructor
@RestController
@RequestMapping("file")
public class FileController {

	private final FileService fileService;

	@Operation(summary = "Download file with UUID")
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
