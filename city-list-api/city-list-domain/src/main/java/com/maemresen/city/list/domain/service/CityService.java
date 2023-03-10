package com.maemresen.city.list.domain.service;

import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.exception.ServiceException;
import com.maemresen.city.list.domain.exception.business.city.CityNotFoundException;
import com.maemresen.city.list.domain.service.model.dto.CityAddCommentRequestDto;
import com.maemresen.city.list.domain.service.model.dto.CityCreateRequestDto;
import com.maemresen.city.list.domain.service.model.dto.CityResponseDto;
import com.maemresen.city.list.domain.service.model.dto.CityUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

/**
 * City related operations like create, update, search etc.
 */
public interface CityService {

	/**
	 * To create a City
	 * @param cityCreateDto city requested to be created
	 * @return created City
	 * @throws ServiceException something went wrong
	 */
	CityResponseDto create(CityCreateRequestDto cityCreateDto) throws ServiceException;

	/**
	 * To update a City
	 * @param cityUpdateRequestDto values requested to be updated
	 * @return updated City
	 * @throws ServiceException something went wrong
	 */
	CityResponseDto update(CityUpdateRequestDto cityUpdateRequestDto) throws ServiceException;

	/**
	 * Filter and get page for cities
	 * @param pageable page request
	 * @param requestParamMap extra filters (it may be an empty then cities will not be filtered)
	 * @return page for cities
	 */
	Page<CityResponseDto> findAll(Pageable pageable, Map<String, String> requestParamMap);

	/**
	 * To find city by id
	 * @param id querying id
	 * @return found City if exists or else empty
	 */
    Optional<CityResponseDto> findById(Long id);

	/**
	 * To download image file and create a city from Cities CSV file
	 * @param citiesCsvInputStream input stream for CSV file
	 * @throws ServiceException something went wrong
	 */
    void importCitesFromCsv(InputStream citiesCsvInputStream) throws ServiceException;

	/**
	 * To delete photo of the city.
	 * @param cityId id of the city that photo will be deleted of
	 * @throws ServiceException something went wrong
	 */
    void deletePhoto(Long cityId) throws ServiceException;

	/**
	 * To update photo of the city
	 * @param cityId id of the city that photo will be updated of
	 * @param file new photo will be set to the City
	 * @throws ServiceException something went wrong
	 */
	void updatePhoto(Long cityId, MultipartFile file) throws ServiceException;

	/**
	 * To add comment to city
	 * @param cityId id of the city the comment will be added
	 * @param user the owner of the comment
	 * @param requestDto comment add request dto
	 */
	void addComment(Long cityId, User user, CityAddCommentRequestDto requestDto) throws CityNotFoundException;
}
