package com.epam.esm.dao.util;

import static com.epam.esm.dao.util.ParamName.*;
import static com.epam.esm.dao.impl.ColumnName.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.validator.ParamValidator;

/**
 * The {@code GiftCertificateQueryBuilder} class builds SQL query for searching
 * gift ñertificates
 * 
 * @author Ihar Klepcha
 */
public class GiftCertificateQueryBuilder {
	private static final String TAGS = "tags";
	private static final String PERCENT = "%";
	private static final String SEPARATOR = ",";

	/**
	 * Builds query find entity by parameters
	 * 
	 * @param params          {@link Map} of {@link String} and {@link String}
	 *                        parameters
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @return {@link CriteriaQuery} of {@link GiftCertificate} query
	 */
	public static CriteriaQuery<GiftCertificate> buildQueryFindByParams(Map<String, String> params,
			CriteriaBuilder criteriaBuilder) {
		CriteriaQuery<GiftCertificate> criteriaQuery = criteriaBuilder.createQuery(GiftCertificate.class);
		Root<GiftCertificate> giftCertificateRoot = criteriaQuery.from(GiftCertificate.class);
		List<Predicate> predicateList = new ArrayList<>();
		if (params.containsKey(TAG)) {
			predicateList.addAll(addTags(params.get(TAG), criteriaBuilder, giftCertificateRoot));
		}
		if (params.containsKey(NAME)) {
			predicateList.add(addName(params.get(NAME), criteriaBuilder, giftCertificateRoot));
		}
		if (params.containsKey(DESCRIPTION)) {
			predicateList.add(addDescription(params.get(DESCRIPTION), criteriaBuilder, giftCertificateRoot));
		}
		criteriaQuery.select(giftCertificateRoot).where(predicateList.toArray(new Predicate[] {}));
		Order order = addSort(params, criteriaBuilder, giftCertificateRoot);
		criteriaQuery.orderBy(order);
		return criteriaQuery;
	}

	/**
	 * Adds tag names to searching query
	 * 
	 * @param tag                 {@link String} tag name
	 * @param criteriaBuilder     {@link CriteriaBuilder} criteria builder
	 * @param giftCertificateRoot {@link Root} of {@link GiftCertificate} root of
	 *                            query
	 * @return {@link List} of {@link Predicate} list of predicates
	 */
	private static List<Predicate> addTags(String tag, CriteriaBuilder criteriaBuilder,
			Root<GiftCertificate> giftCertificateRoot) {
		List<String> tagNameList = Arrays.asList(tag.split(SEPARATOR));
		return tagNameList.stream()
				.map(name -> criteriaBuilder.equal(giftCertificateRoot.join(TAGS).get(TAGS_NAME), name))
				.collect(Collectors.toList());
	}

	/**
	 * Adds name to searching query
	 * 
	 * @param name                {@link String} gift certificate name
	 * @param criteriaBuilder     {@link CriteriaBuilder} criteria builder
	 * @param giftCertificateRoot {@link Root} of {@link GiftCertificate} root of
	 *                            query
	 * @return {@link Predicate} predicate
	 */
	private static Predicate addName(String name, CriteriaBuilder criteriaBuilder,
			Root<GiftCertificate> giftCertificateRoot) {
		return criteriaBuilder.like(giftCertificateRoot.get(GIFT_CERTIFICATES_NAME), PERCENT + name + PERCENT);
	}

	/**
	 * Adds description to searching query
	 * 
	 * @param description         {@link String} gift certificate description
	 * @param criteriaBuilder     {@link CriteriaBuilder} criteria builder
	 * @param giftCertificateRoot {@link Root} of {@link GiftCertificate} root of
	 *                            query
	 * @return {@link Predicate} predicate
	 */
	private static Predicate addDescription(String description, CriteriaBuilder criteriaBuilder,
			Root<GiftCertificate> giftCertificateRoot) {
		return criteriaBuilder.like(
				giftCertificateRoot.get(GIFT_CERTIFICATES_DESCRIPTION), PERCENT + description + PERCENT);
	}

	/**
	 * Adds sort type to query
	 * 
	 * @param params              {@link Map} of {@link String} and {@link String}
	 *                            parameters
	 * @param criteriaBuilder     {@link CriteriaBuilder} criteria builder
	 * @param giftCertificateRoot {@link Root} of {@link GiftCertificate} root of
	 *                            query
	 * @return {@link Order} order
	 */
	private static Order addSort(Map<String, String> params, CriteriaBuilder criteriaBuilder,
			Root<GiftCertificate> giftCertificateRoot) {
		ParamValidator.validateSortParam(params);
		String sortBy = params.getOrDefault(SORT_BY, SortType.ID.toString().toLowerCase());
		String orderBy = params.getOrDefault(ORDER_BY, OrderType.ASC.toString());
		return (OrderType.valueOf(orderBy.toUpperCase()) == OrderType.ASC)
				? criteriaBuilder.asc(giftCertificateRoot.get(sortBy))
				: criteriaBuilder.desc(giftCertificateRoot.get(sortBy));
	}

	/**
	 * Builds query find entity by name
	 * 
	 * @param name            {@link String} name gift certificate
	 * @param criteriaBuilder {@link CriteriaBuilder} criteria builder
	 * @return {@link CriteriaQuery} of {@link GiftCertificate} query
	 */
	public static CriteriaQuery<GiftCertificate> buildQueryFindEntityByName(String name,
			CriteriaBuilder criteriaBuilder) {
		CriteriaQuery<GiftCertificate> criteriaQuery = criteriaBuilder.createQuery(GiftCertificate.class);
		Root<GiftCertificate> giftCertificateRoot = criteriaQuery.from(GiftCertificate.class);
		criteriaQuery.select(giftCertificateRoot);
		criteriaQuery.where(criteriaBuilder.like(giftCertificateRoot.get(GIFT_CERTIFICATES_NAME), name));
		return criteriaQuery;
	}
}
