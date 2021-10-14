package com.epam.esm.dao;

import java.util.List;
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
	 * Looks for an entity where entity has name
	 * 
	 * @param giftCertificateId is gift certificate id
	 * @return {@link List} of {@link Tag} entity received from database
	 */
	List<Tag> findEntityByGiftCertificate(long giftCertificateId);

	/**
	 * Looks for the most widely used tag of a user with the highest cost of all orders
	 * 
	 * @return {@link Optional} of {@link Tag} entity received from database
	 */
	Optional<Tag> findMostPopularTagOfUserWithHighestCostOfAllOrders();
}
