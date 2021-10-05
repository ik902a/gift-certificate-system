package com.epam.esm.service;

import java.util.List;

import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;

/**
 * The {@code TagService} interface for operations with the tag
 * 
 * @author Ihar Klepcha
 */
public interface TagService {

	/**
	 * Creates tag in database
	 * 
	 * @param tag {@link Tag} tag
	 * @return {@link Tag} received from database
	 */
	TagDto create(TagDto tagdto);

	/**
	 * Finds all tags
	 * 
	 * @return {@link List} of {@link Tag} received from database
	 */
	List<TagDto> findAll();

	/**
	 * Finds tag by id
	 * 
	 * @param id {@link long} tag id
	 * @return {@link Tag} received from database
	 */
	TagDto findById(long id);

	/**
	 * deletes tag
	 * 
	 * @param id {@link long} tag id
	 */
	void delete(long id);
}
