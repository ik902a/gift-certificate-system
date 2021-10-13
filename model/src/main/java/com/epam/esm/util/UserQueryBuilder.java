package com.epam.esm.util;

import static com.epam.esm.entity.ParamName.LOGIN;
import static com.epam.esm.entity.ParamName.ORDER_BY;
import static com.epam.esm.entity.ParamName.SORT_BY;

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
import com.epam.esm.entity.OrderType;
import com.epam.esm.entity.SortType;
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
	
	public static CriteriaQuery<User> buildQuery(Map<String, String> params, CriteriaBuilder criteriaBuilder) {
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from(User.class);
		List<Predicate> predicateList = new ArrayList<>();
		if (params.containsKey(LOGIN.toString().toLowerCase())) {
			predicateList.add(addLogin(params.get(LOGIN.toString().toLowerCase()), criteriaBuilder, userRoot));
		}
		criteriaQuery.select(userRoot).where(predicateList.toArray(new Predicate[] {}));
		Order order = addSort(params, criteriaBuilder, userRoot);
		criteriaQuery.orderBy(order);
		log.info("SQL criteria query Builder");
		return criteriaQuery;
	}

	private static Predicate addLogin(String login, CriteriaBuilder criteriaBuilder, Root<User> userRoot) {
		return criteriaBuilder.like(userRoot.get(ColumnName.USERS_LOGIN), PERCENT + login + PERCENT);
	}
	
	private static Order addSort(Map<String, String> params, CriteriaBuilder criteriaBuilder, Root<User> userRoot) {
		ParamValidator.validateSortParam(params);
		String sortBy = params.getOrDefault(SORT_BY.toString().toLowerCase(), SortType.ID.toString().toLowerCase());
		String orderBy = params.getOrDefault(ORDER_BY.toString().toLowerCase(), OrderType.ASC.toString());
		return (OrderType.valueOf(orderBy) == OrderType.ASC)
				? criteriaBuilder.asc(userRoot.get(sortBy))
				: criteriaBuilder.desc(userRoot.get(sortBy));
	}
}
