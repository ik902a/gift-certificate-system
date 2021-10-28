package com.epam.esm.dao.util;

import static com.epam.esm.dao.util.ParamName.LOGIN;
import static com.epam.esm.dao.util.ParamName.ORDER_BY;
import static com.epam.esm.dao.util.ParamName.SORT_BY;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.esm.dao.impl.ColumnName;
import com.epam.esm.entity.User;
import com.epam.esm.validator.ParamValidator;

/**
 * The {@code UserQueryBuilder} class builds SQL query for searching users
 * 
 * @author Ihar Klepcha
 */
public class UserQueryBuilder {
	public static Logger log = LogManager.getLogger();
	private static final String PERCENT = "%";
	
	/**
	 * Builds query
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @return {@link CriteriaQuery} of {@link User} query
	 */
	public static CriteriaQuery<User> buildQuery(Map<String, String> params, CriteriaBuilder criteriaBuilder) {
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from(User.class);
		List<Predicate> predicateList = new ArrayList<>();
		if (params.containsKey(LOGIN)) {
			predicateList.add(addLogin(params.get(LOGIN), criteriaBuilder, userRoot));
		}
		criteriaQuery.select(userRoot).where(predicateList.toArray(new Predicate[] {}));
		Order order = addSort(params, criteriaBuilder, userRoot);
		criteriaQuery.orderBy(order);
		log.info("SQL criteria query Builder");
		return criteriaQuery;
	}

	/**
	 * Adds login to searching query
	 * 
	 * @param name {@link String} gift certificate name
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @param giftCertificateRoot {@link Root} of {@link User} root of query
	 * @return {@link Predicate} predicate
	 */
	private static Predicate addLogin(String login, CriteriaBuilder criteriaBuilder, Root<User> userRoot) {
		return criteriaBuilder.like(userRoot.get(ColumnName.USERS_LOGIN), PERCENT + login + PERCENT);
	}
	
	/**
	 * Adds sort type to query
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @param giftCertificateRoot {@link Root} of {@link User} root of query
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
