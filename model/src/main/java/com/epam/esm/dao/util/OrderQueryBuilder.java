package com.epam.esm.dao.util;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.epam.esm.entity.User;
import com.epam.esm.entity.Order;

/**
 * The {@code OrderQueryBuilder} class builds SQL query for searching order
 * 
 * @author Ihar Klepcha
 */
public class OrderQueryBuilder {
	private static final String USER = "user";
	
	/**
	 * Builds query find entity by parameters
	 * 
	 * @param user {@link User} user owner
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @return {@link CriteriaQuery} of {@link Order} query
	 */
	public static CriteriaQuery<Order> buildQueryFindByUser(User user, Map<String, String> params, 
			CriteriaBuilder criteriaBuilder) {
		CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
		Root<Order> orderRoot = criteriaQuery.from(Order.class);
		criteriaQuery.select(orderRoot).where(criteriaBuilder.equal(orderRoot.get(USER), user));
		return criteriaQuery;
	}
}
