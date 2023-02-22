package com.maemresen.city.list.rest.controller;

import com.maemresen.city.list.domain.error.exception.base.ServiceException;
import com.maemresen.city.list.domain.error.exception.city.CityNotFoundException;
import com.maemresen.city.list.domain.service.CityService;
import com.maemresen.city.list.domain.service.model.dto.CityResponseDto;
import com.maemresen.city.list.domain.service.model.dto.CityUpdateRequestDto;
import com.maemresen.city.list.domain.util.constants.RoleNames;
import com.maemresen.city.list.rest.config.GenericResponse;
import com.maemresen.city.list.rest.config.ServletUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Tag(name = "City Controller", description = "City related operations like listing, editing etc.")
@RequiredArgsConstructor
@RestController
@RequestMapping("city")
public class CityController {

	private final CityService cityService;

	@Operation(summary = "List cities")
	@GetMapping
	public GenericResponse<Page<CityResponseDto>> findAll(Pageable pageable, @RequestParam Map<String, String> reqestParamMap) {
		return GenericResponse.ok(cityService.findAll(pageable, reqestParamMap));
	}

	@Operation(summary = "List cities")
	@GetMapping("/{id}")
	public GenericResponse<CityResponseDto> findById(@PathVariable("id") long id) throws ServiceException {
		return cityService.findById(id)
			.map(GenericResponse::ok)
			.orElseThrow(() -> new CityNotFoundException(id));
	}

	@Secured(RoleNames.ROLE_ALLOW_EDIT)
	@Operation(summary = "Update City")
	@PutMapping
	public GenericResponse<CityResponseDto> update(@RequestBody @Valid CityUpdateRequestDto requestDto) throws ServiceException {
		return GenericResponse.ok(cityService.update(requestDto));
	}

	@Operation(summary = "Update Photo of the city")
	@PostMapping( "/photo/{cityId}")
	public void updatePhoto(@PathVariable("cityId")Long cityId, @RequestParam("file") MultipartFile file, HttpServletResponse response) throws ServiceException, IOException {
		cityService.updatePhoto(cityId, file);
		ServletUtils.setOkResponse(response);
	}

	@Operation(summary = "Delete Photo of the city")
	@DeleteMapping( "/photo/{cityId}")
	public GenericResponse<Boolean> deletePhoto(@PathVariable("cityId") Long cityId) throws ServiceException {
		cityService.deltePhoto(cityId);
		return GenericResponse.ok();
	}
}
