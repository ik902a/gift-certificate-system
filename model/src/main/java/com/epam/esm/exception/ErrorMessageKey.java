package com.epam.esm.exception;

/**
 * The {@code ErrorMessageKey} enum describes errors messages keys
 * 
 * @author Ihar Klepcha
 */
public enum ErrorMessageKey {
	
	RESOURCE_NOT_FOUND_BY_ID ("message.resource_not_found_by_id"),
	RESOURCE_NOT_FOUND_BY_NAME ("resource_not_found_by_name"),
	NOT_FOUND_POPULAR_TAG ("message.not_found_popular_tag"),
	INCORRECT_SORT_PARAM ("message.incorrect_sort_param"),
	INCORRECT_ORDER_PARAM ("message.incorrect_order_param"),
	INCORRECT_OFFSET_PARAM ("message.incorrect_offset_param"),
	INCORRECT_LIMIT_PARAM ("message.incorrect_limit_param"),
	INTERNAL_SERVER_ERROR ("message.internal_server_error");
	
	private final String errorMessageKey;

	private ErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}

	public String getErrorMessageKey() {
		return errorMessageKey;
	}
}
