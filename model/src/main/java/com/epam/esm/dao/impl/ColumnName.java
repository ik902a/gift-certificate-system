package com.epam.esm.dao.impl;

/**
 * The {@code ColumnName} class describes all column name
 * 
 * @author Ihar Klepcha
 */
public final class ColumnName {
	// gift_certificates
	public static final String GIFT_CERTIFICATES_ID = "id";
	public static final String GIFT_CERTIFICATES_NAME = "name";
	public static final String GIFT_CERTIFICATES_DESCRIPTION = "description";
	public static final String GIFT_CERTIFICATES_PRICE = "price";
	public static final String GIFT_CERTIFICATES_DURATION = "duration";
	public static final String GIFT_CERTIFICATES_CREATE_DATE = "create_date";
	public static final String GIFT_CERTIFICATES_LAST_UPDATE_DATE = "last_update_date";
	// tags
	public static final String TAGS_ID = "id";
	public static final String TAGS_NAME = "name";
	// users
	public static final String USERS_ID = "id";
	public static final String USERS_LOGIN = "login";
	// orders
	public static final String ORDERS_ID = "id";
	public static final String ORDERS_DATE = "date";
	public static final String ORDERS_COST = "cost";
	public static final String ORDERS_USER_ID = "user_id";

	private ColumnName() {
	}
}
