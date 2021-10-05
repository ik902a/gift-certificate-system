package com.epam.esm.util;

import static com.epam.esm.entity.ParamName.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.esm.dao.impl.ColumnName;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.OrderType;
import com.epam.esm.entity.SortType;
import com.epam.esm.entity.Tag;
//import com.epam.esm.validator.ParamValidator;

/**
 * The {@code SelectGiftCertificateSqlQueryBuilder} class builds SQL query for searching gift ñertificates
 * 
 * @author Ihar Klepcha
 */
public class GiftCertificateQueryBuilder {
	public static Logger log = LogManager.getLogger();
	private static final String TAGS = "tags";
	private static final String PERCENT = "%";
	
	public static CriteriaQuery<GiftCertificate> buildQuery(Map<String, String> params,
			CriteriaBuilder criteriaBuilder) {
		CriteriaQuery<GiftCertificate> criteriaQuery = criteriaBuilder.createQuery(GiftCertificate.class);
		Root<GiftCertificate> giftCertificateRoot = criteriaQuery.from(GiftCertificate.class);
		List<Predicate> predicateList = new ArrayList<>();

		if (params.containsKey(TAG.toString().toLowerCase())) {
			predicateList.add(addTags(params.get(TAG.toString().toLowerCase()), criteriaBuilder, giftCertificateRoot));
		}
		if (params.containsKey(NAME.toString().toLowerCase())) {
			predicateList.add(addName(params.get(NAME.toString().toLowerCase()), criteriaBuilder, giftCertificateRoot));
		}
		if (params.containsKey(DESCRIPTION.toString().toLowerCase())) {
			predicateList.add(addDescription(params.get(DESCRIPTION.toString().toLowerCase()), criteriaBuilder,
					giftCertificateRoot));
		}
		criteriaQuery.select(giftCertificateRoot).where(predicateList.toArray(new Predicate[] {}));
		Order order = addSort(params, criteriaBuilder, giftCertificateRoot);
		criteriaQuery.orderBy(order);
		log.info("SQL criteria query Builder");
		return criteriaQuery;
	}
	
	private static Predicate addTags(String tag, CriteriaBuilder criteriaBuilder,
			Root<GiftCertificate> giftCertificateRoot) {
		Join<GiftCertificate, Tag> tagTable = giftCertificateRoot.join(TAGS);
		Predicate predicate = criteriaBuilder.equal(tagTable.get(ColumnName.TAGS_NAME), tag);
		return predicate;
	}
	
	private static Predicate addName(String name, CriteriaBuilder criteriaBuilder
			, Root<GiftCertificate> giftCertificateRoot) {
		Predicate predicate = criteriaBuilder.like(giftCertificateRoot.get(ColumnName.GIFT_CERTIFICATES_NAME),
				PERCENT + name + PERCENT);
		return predicate;
	}

	private static Predicate addDescription(String description, CriteriaBuilder criteriaBuilder,
			Root<GiftCertificate> giftCertificateRoot) {
		Predicate predicate = criteriaBuilder.like(giftCertificateRoot.get(ColumnName.GIFT_CERTIFICATES_DESCRIPTION),
				PERCENT + description + PERCENT);
		return predicate;
	}
	
	private static Order addSort(Map<String, String> params, CriteriaBuilder criteriaBuilder,
			Root<GiftCertificate> giftCertificateRoot) {
//		   ParamValidator.validateSortParam(params); 
		String sortBy = params.getOrDefault(SORT_BY.toString().toLowerCase(), SortType.NAME.toString().toLowerCase());
		String orderBy = params.getOrDefault(ORDER_BY.toString().toLowerCase(), OrderType.ASC.toString());
		Order order = (OrderType.valueOf(orderBy) == OrderType.ASC)
				? criteriaBuilder.asc(giftCertificateRoot.get(sortBy))
				: criteriaBuilder.desc(giftCertificateRoot.get(sortBy));
		return order;
	}
}
