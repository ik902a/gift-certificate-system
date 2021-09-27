package com.epam.esm.service;

import java.util.List;

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
	Tag create(Tag tag);

	/**
	 * Finds all tags
	 * 
	 * @return {@link List} of {@link Tag} received from database
	 */
	List<Tag> findAll();

	/**
	 * Finds tag by id
	 * 
	 * @param id {@link long} tag id
	 * @return {@link Tag} received from database
	 */
	Tag findById(long id);

	/**
	 * deletes tag
	 * 
	 * @param id {@link long} tag id
	 */
	void delete(long id);
}
