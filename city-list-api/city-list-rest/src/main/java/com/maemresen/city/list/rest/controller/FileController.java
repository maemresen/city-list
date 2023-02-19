package com.maemresen.city.list.rest.controller;

import com.maemresen.city.list.domain.error.exception.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;

@RequiredArgsConstructor
@RestController
@RequestMapping("file")
public class FileController {

	@GetMapping(value = "{uuid}")
	public void getFile(@PathVariable("uuid") String uuid, HttpServletResponse response) throws ServiceException {
		try (InputStream fileInputStream = new FileInputStream("downloads/" + uuid)) {
			IOUtils.copy(fileInputStream, response.getOutputStream());
			response.flushBuffer();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
