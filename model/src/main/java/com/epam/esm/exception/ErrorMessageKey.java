package com.epam.esm.exception;

/**
 * The {@code ErrorMessageKey} class describes errors messages keys
 * 
 * @author Ihar Klepcha
 */
public final class ErrorMessageKey {
	public static final String RESOURCE_NOT_FOUND_BY_ID = "message.resource_not_found_by_id";
	public static final String RESOURCE_NOT_FOUND_BY_NAME = "message.resource_not_found_by_name";
	public static final String NOT_FOUND_POPULAR_TAG = "message.not_found_popular_tag";
	public static final String INCORRECT_SORT_PARAM = "message.incorrect_sort_param";
	public static final String INCORRECT_ORDER_PARAM = "message.incorrect_order_param";
	public static final String INCORRECT_OFFSET_PARAM = "message.incorrect_offset_param";
	public static final String INCORRECT_LIMIT_PARAM = "message.incorrect_limit_param";
	public static final String INCORRECT_VALUE_TYPE = "message.incorrect_value_type";
	public static final String DATABASE_ERROR = "message.database_error";
	public static final String BAD_CREDENTIALS = "message.bad_credentials";
	public static final String NO_ACCESS = "message.no_access";
	public static final String LOCKED_ACCOUNT = "message.locked_account";
	public static final String INTERNAL_SERVER_ERROR = "message.internal_server_error";

	private ErrorMessageKey() {
	}
}
