package com.epam.esm.dao;

import java.util.Optional;

import com.epam.esm.entity.Tag;

/**
 * The {@code TagDao} interface works with tags table in database
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface TagDao extends BaseDao<Tag> {

	/**
	 * Looks for the most widely used tag of a user with the highest cost of all orders
	 * 
	 * @return {@link Optional} of {@link Tag} entity received from database
	 */
	Optional<Tag> findMostPopularTagOfUserWithHighestCostOfAllOrders();
}
