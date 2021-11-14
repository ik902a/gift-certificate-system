package com.epam.esm.exception;

/**
 * The {@code ErrorCode} class describes errors codes
 * 
 * @author Ihar Klepcha
 */
public final class ErrorCode {
	public static final String GIFT_CERTIFICATE_INCORRECT = "01";
	public static final String TAG_INCORRECT = "02";
	public static final String USER_INCORRECT = "03";
	public static final String ORDER_INCORRECT = "04";
	public static final String INCORRECT_PARAM = "12";
	public static final String UNAUTHORIZED = "41";
	public static final String FORBIDDEN = "43";
	public static final String DEFAULT_ERROR = "00";

	private ErrorCode() {
	}
}
