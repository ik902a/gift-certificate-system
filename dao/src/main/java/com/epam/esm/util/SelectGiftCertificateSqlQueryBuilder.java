package com.epam.esm.util;

import static com.epam.esm.entity.ParamName.*;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.esm.entity.OrderType;
import com.epam.esm.entity.SortType;
import com.epam.esm.validator.ParamValidator;

/**
 * The {@code SelectGiftCertificateSqlQueryBuilder} class builds SQL query for searching gift ñertificates
 * 
 * @author Ihar Klepcha
 */
public class SelectGiftCertificateSqlQueryBuilder {
	public static Logger log = LogManager.getLogger();
	private static final String SQL_FIND_GIFT_CERTIFICATES = "SELECT id, name, description, price, "
			+ "duration, create_date, last_update_date FROM gift_certificates";
	private static final String SQL_FIND_GIFT_CERTIFICATE_BY_TAG = "id IN "
			+ "(SELECT gift_certificate_id FROM gift_certificates_tags "
			+ "JOIN tags ON gift_certificates_tags.tag_id = tags.id WHERE tags.name=?)";
	private static final String SQL_FIND_GIFT_CERTIFICATE_BY_NAME = "name LIKE CONCAT('%',?,'%')";
	private static final String SQL_FIND_GIFT_CERTIFICATE_BY_DESCRIPTION = "description LIKE CONCAT('%',?,'%')";
	private static final String SQL_SPACE = " ";
	private static final String SQL_QUESTION = "?";
	private static final String SQL_QUOTATION ="'";
	private static final String SQL_WHERE ="WHERE";
	private static final String SQL_AND = "AND";
	private static final String SQL_ORDER_BY ="ORDER BY";
	private static final String SQL_LAST_UPDATE = "last_update_";
	
	/**
	 * Builds SQL query
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return {@link String} sql query
	 */
	public static String buildQuery(Map<String, String> params) {
		StringBuilder query = new StringBuilder();
		query.append(SQL_FIND_GIFT_CERTIFICATES);
		if (!params.isEmpty()) {
			query.append(buildParams(params));
			query.append(buildSort(params));
		}
		log.info("SQL query - " + query.toString());
		return query.toString();
	}

	/**
	 * Builds SQL query by search request
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return {@link String} sql query
	 */
	private static String buildParams(Map<String, String> params) {
			StringBuilder query = new StringBuilder();
			boolean firstParamFlag = true;
		if (params.containsKey(TAG.toString().toLowerCase())) {
			query.append(SQL_SPACE).append(SQL_WHERE);
			String tagName = SQL_QUOTATION + params.get(TAG.toString().toLowerCase()) + SQL_QUOTATION;
			query.append(SQL_SPACE);
			query.append(StringUtils.replace(SQL_FIND_GIFT_CERTIFICATE_BY_TAG, SQL_QUESTION, tagName));
			firstParamFlag = false;
		}
		if (params.containsKey(NAME.toString().toLowerCase())) {
			query.append(SQL_SPACE);
			query.append(firstParamFlag ? SQL_WHERE : SQL_AND);
			String name = SQL_QUOTATION + params.get(NAME.toString().toLowerCase()) + SQL_QUOTATION;
			query.append(SQL_SPACE);
			query.append(StringUtils.replace(SQL_FIND_GIFT_CERTIFICATE_BY_NAME, SQL_QUESTION, name));
			firstParamFlag = false;
		}
		
		if (params.containsKey(DESCRIPTION.toString().toLowerCase())) {
			query.append(SQL_SPACE);
			query.append(firstParamFlag ? SQL_WHERE : SQL_AND);
			String description = SQL_QUOTATION + params.get(DESCRIPTION.toString().toLowerCase()) + SQL_QUOTATION;
			query.append(SQL_SPACE);
			query.append(StringUtils.replace(SQL_FIND_GIFT_CERTIFICATE_BY_DESCRIPTION, SQL_QUESTION, description));
		}
		return query.toString();
	}
	
	/**
	 * Builds SQL query by sort param
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return {@link String} sql query
	 */
	private static String buildSort(Map<String, String> params) {
		StringBuilder query = new StringBuilder();
		ParamValidator.validateSortParam(params);
		String sortBy = params.getOrDefault(SORT_BY.toString().toLowerCase(), SortType.NAME.toString().toLowerCase());
		String orderBy = params.getOrDefault(ORDER_BY.toString().toLowerCase(), OrderType.ASC.toString());
		if (sortBy.equalsIgnoreCase(SortType.DATE.toString())) {
			sortBy = SQL_LAST_UPDATE + sortBy;
		}
		query.append(SQL_SPACE).append(SQL_ORDER_BY);
		query.append(SQL_SPACE).append(sortBy);
		query.append(SQL_SPACE).append(orderBy);
		return query.toString();
	}
}
