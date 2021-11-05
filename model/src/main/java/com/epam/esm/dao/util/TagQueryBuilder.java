package com.epam.esm.dao.util;

import static com.epam.esm.dao.impl.ColumnName.*;
import static com.epam.esm.dao.util.ParamName.*;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.epam.esm.entity.Tag;
import com.epam.esm.validator.ParamValidator;

/**
 * The {@code TagQueryBuilder} class builds SQL query for searching tags
 * 
 * @author Ihar Klepcha
 */
public class TagQueryBuilder {
	
	/**
	 * Builds query find entity by parameters
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @return {@link CriteriaQuery} of {@link Tag} query
	 */
	public static CriteriaQuery<Tag> buildQueryFindByParams(Map<String, String> params, 
			CriteriaBuilder criteriaBuilder) {
		CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
		Root<Tag> tagRoot = criteriaQuery.from(Tag.class);
		criteriaQuery.select(tagRoot);
		Order order = addSort(params, criteriaBuilder, tagRoot);
		criteriaQuery.orderBy(order);
		return criteriaQuery;
	}
	
	/**
	 * Builds query find entity by name
	 * 
	 * @param name {@link String} name tag
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @return {@link CriteriaQuery} of {@link Tag} query
	 */
	public static CriteriaQuery<Tag> buildQueryFindEntityByName(String name, CriteriaBuilder criteriaBuilder) {
		CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
		Root<Tag> tagRoot = criteriaQuery.from(Tag.class);
		criteriaQuery.select(tagRoot).where(criteriaBuilder.like(tagRoot.get(TAGS_NAME), name));
		return criteriaQuery;
	}
	
	/**
	 * Adds sort type to query
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @param userRoot {@link Root} of {@link Tag} root of query
	 * @return {@link Order} order
	 */
	private static Order addSort(Map<String, String> params, CriteriaBuilder criteriaBuilder, Root<Tag> tagRoot) {
		ParamValidator.validateSortParam(params);
		String sortBy = params.getOrDefault(SORT_BY, SortType.ID.toString().toLowerCase());
		String orderBy = params.getOrDefault(ORDER_BY, OrderType.ASC.toString());
		return (OrderType.valueOf(orderBy) == OrderType.ASC)
				? criteriaBuilder.asc(tagRoot.get(sortBy))
				: criteriaBuilder.desc(tagRoot.get(sortBy));
	}
}
