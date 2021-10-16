package com.epam.esm.util;

import java.util.Map;

import com.epam.esm.validator.ParamValidator;

public class PaginationParamExtractor {
	private static final String DEFAULT_OFFSET = "0";
	private static final String DEFAULT_LIMIT = "5";
	
	public static int getOffset(Map<String, String> params) {
		ParamValidator.validatePaginationParam(params);
		return Integer.parseInt(params.getOrDefault(ParamName.OFFSET.toString().toLowerCase(), DEFAULT_OFFSET));
	}
	
	public static int getLimit(Map<String, String> params) {
		ParamValidator.validatePaginationParam(params);
		return Integer.parseInt(params.getOrDefault(ParamName.LIMIT.toString().toLowerCase(), DEFAULT_LIMIT));
	}
}
