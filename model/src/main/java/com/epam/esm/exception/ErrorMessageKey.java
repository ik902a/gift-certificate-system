package com.epam.esm.exception;

/**
 * The {@code ErrorMessageKey} enum describes errors messages keys
 * 
 * @author Ihar Klepcha
 */
public enum ErrorMessageKey {
	RESOURCE_NOT_FOUND_BY_ID ("message.resource_not_found_by_id"),
	INCORRECT_ID ("message.incorrect_id"),
	INCORRECT_NAME ("message.incorrect_name"),
	NAME_EXIST ("message.name_exist"),
	INCORRECT_DESCRIPTION ("message.incorrect_description"),
	INCORRECT_PRICE ("message.incorrect_price"),
	INCORRECT_DURATION ("message.incorrect_duration"), 
	INCORRECT_TAG_NAME ("message.incorrect_tag_name"),
	METHOD_NOT_ALLOWED ("message.method_not_allowed"),
	INCORRECT_PARAM_FORMAT ("message.incorrect_param_format"),
	INCORRECT_PARAM("message.incorrect_param"),
	INCORRECT_SORT_PARAM("message.incorrect_sort_param"),
	INCORRECT_ORDER_PARAM("message.incorrect_order_param"),
	INTERNAL_SERVER_ERROR ("message.internal_server_error");
	
	private final String errorMessageKey;

	private ErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}

	public String getErrorMessageKey() {
		return errorMessageKey;
	}
}
