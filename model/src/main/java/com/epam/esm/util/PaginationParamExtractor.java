package com.epam.esm.util;

import java.util.Map;

import com.epam.esm.validator.ParamValidator;

/**
 * The {@code PaginationParamExtractor} class extracts pagination data
 * 
 * @author Ihar Klepcha
 */
public class PaginationParamExtractor {
	private static final String DEFAULT_OFFSET = "0";
	private static final String DEFAULT_LIMIT = "5";
	
	/**
	 * Extracts offset
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return offset value
	 */
	public static int getOffset(Map<String, String> params) {
		ParamValidator.validatePaginationParam(params);
		return Integer.parseInt(params.getOrDefault(ParamName.OFFSET.toString().toLowerCase(), DEFAULT_OFFSET));
	}
	
	/**
	 * Extracts limit
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return limit value
	 */
	public static int getLimit(Map<String, String> params) {
		ParamValidator.validatePaginationParam(params);
		return Integer.parseInt(params.getOrDefault(ParamName.LIMIT.toString().toLowerCase(), DEFAULT_LIMIT));
	}
}
