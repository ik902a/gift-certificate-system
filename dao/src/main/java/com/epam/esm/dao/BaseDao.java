package com.epam.esm.dao;

import java.util.Optional;

import com.epam.esm.entity.Entity;

/**
 * The {@code BaseDao} is the root interface in the DAO hierarchy
 * 
 * @author Ihar Klepcha
 * @param <T> entity
 */
public interface BaseDao<T extends Entity> {

	/**
	 * Creates a record in the corresponding database table
	 * 
	 * @param t {@link T} entity
	 * @return {@link T} if the record has been created
	 */
	T create(T t);

	/**
	 * Looks for an entity where entity has id
	 * 
	 * @param id is entity id
	 * @return {@link Optional} of {@link T} entity received from database
	 */
	Optional<T> findEntityById(long id);

	/**
	 * Looks for an entity where entity has name
	 * 
	 * @param name {@link String} entity name
	 * @return {@link Optional} of {@link T} entity received from database
	 */
	Optional<T> findEntityByName(String name);

	/**
	 * Deletes a record in the corresponding database table
	 * 
	 * @param id is entity id
	 * @return boolean true if the record has been deleted, else false
	 */
	boolean delete(long id);
}
