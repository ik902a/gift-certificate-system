package com.epam.esm.service;

import java.util.Map;

import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.TagDto;;

/**
 * The {@code TagService} interface for operations with the tag
 * 
 * @author Ihar Klepcha
 */
public interface TagService {

	/**
	 * Creates tag in database
	 * 
	 * @param tag {@link TagDto} tag
	 * @return {@link TagDto} received from database
	 */
	TagDto create(TagDto tagdto);

	/**
	 * Finds all tags
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return {@link PageDto} of {@link TagDto} received from database
	 */
	PageDto<TagDto> find(Map<String, String> params);

	/**
	 * Finds tag by id
	 * 
	 * @param id is tag id
	 * @return {@link TagDto} received from database
	 */
	TagDto findById(long id);

	/**
	 * Deletes tag
	 * 
	 * @param id is tag id
	 */
	void delete(long id);

	/**
	 * Finds the most widely used tag of a user with the highest cost of all orders
	 * 
	 * @return {@link TagDto} received from database
	 */
	TagDto findMostPopularTagOfUserWithHighestCostOfAllOrders();
}
