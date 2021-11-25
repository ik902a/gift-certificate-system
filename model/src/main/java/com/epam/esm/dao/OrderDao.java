package com.epam.esm.dao;

import java.util.List;
import java.util.Map;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;

/**
 * The {@code OrderDao} interface works with orders table in database
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface OrderDao extends BaseDao<Order> {

	/**
	 * Looks for an entity where entity has owner user
	 * 
	 * @param user   {@link User} user for searching
	 * @param params {@link Map} of {@link String} and {@link String} parameters for
	 *               searching
	 * @return {@link List} of {@link Order} entity received from database
	 */
	List<Order> findOrdersByUser(User user, Map<String, String> params);

	/**
	 * Gets number of entities in query
	 * 
	 * @param user   {@link User} user for searching
	 * @param params {@link Map} of {@link String} and {@link String} parameters for
	 *               searching
	 * @return number entities received from database
	 */
	long getTotalNumberByUser(User user, Map<String, String> params);
}
