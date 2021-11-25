package com.epam.esm.dao.util;

import java.util.Map;

import com.epam.esm.validator.ParamValidator;

/**
 * The {@code PaginationParamExtractor} class extracts pagination data
 * 
 * @author Ihar Klepcha
 */
public class PaginationParamExtractor {
	private static final String DEFAULT_OFFSET = "0";
	private static final String DEFAULT_LIMIT = "20";

	/**
	 * Extracts offset
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return offset value
	 */
	public static int getOffset(Map<String, String> params) {
		ParamValidator.validatePaginationParam(params);
		params.put(ParamName.OFFSET, params.getOrDefault(ParamName.OFFSET, DEFAULT_OFFSET));
		return Integer.parseInt(params.get(ParamName.OFFSET));
	}

	/**
	 * Extracts limit
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return limit value
	 */
	public static int getLimit(Map<String, String> params) {
		ParamValidator.validatePaginationParam(params);
		params.put(ParamName.LIMIT, params.getOrDefault(ParamName.LIMIT, DEFAULT_LIMIT));
		return Integer.parseInt(params.get(ParamName.LIMIT));
	}
}
