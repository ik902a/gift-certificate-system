package com.epam.esm.dao.util;

import static com.epam.esm.dao.util.ParamName.*;
import static com.epam.esm.dao.impl.ColumnName.*;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.epam.esm.entity.User;
import com.epam.esm.validator.ParamValidator;

/**
 * The {@code UserQueryBuilder} class builds SQL query for searching users
 * 
 * @author Ihar Klepcha
 */
public class UserQueryBuilder {
	private static final String PERCENT = "%";
	
	/**
	 * Builds query find entity by parameters
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @return {@link CriteriaQuery} of {@link User} query
	 */
	public static CriteriaQuery<User> buildQueryFindByParams(Map<String, String> params, 
			CriteriaBuilder criteriaBuilder) {
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from(User.class);
		criteriaQuery.select(userRoot);
		if (params.containsKey(LOGIN)) {
			criteriaQuery.where(addLogin(params.get(LOGIN), criteriaBuilder, userRoot));
		}
		Order order = addSort(params, criteriaBuilder, userRoot);
		criteriaQuery.orderBy(order);
		return criteriaQuery;
	}

	/**
	 * Adds login to searching query
	 * 
	 * @param login {@link String} user login
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @param userRoot {@link Root} of {@link User} root of query
	 * @return {@link Predicate} predicate
	 */
	private static Predicate addLogin(String login, CriteriaBuilder criteriaBuilder, Root<User> userRoot) {
		return criteriaBuilder.like(userRoot.get(USERS_LOGIN), PERCENT + login + PERCENT);
	}
	
	/**
	 * Adds sort type to query
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @param userRoot {@link Root} of {@link User} root of query
	 * @return {@link Order} order
	 */
	private static Order addSort(Map<String, String> params, CriteriaBuilder criteriaBuilder, Root<User> userRoot) {
		ParamValidator.validateSortParam(params);
		String sortBy = params.getOrDefault(SORT_BY, SortType.ID.toString().toLowerCase());
		String orderBy = params.getOrDefault(ORDER_BY, OrderType.ASC.toString());
		return (OrderType.valueOf(orderBy) == OrderType.ASC)
				? criteriaBuilder.asc(userRoot.get(sortBy))
				: criteriaBuilder.desc(userRoot.get(sortBy));
	}
}
