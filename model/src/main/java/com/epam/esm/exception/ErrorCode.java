package com.epam.esm.exception;

/**
 * The {@code ErrorCode} enum describes errors codes
 * 
 * @author Ihar Klepcha
 */
public enum ErrorCode {
	

	GIFT_CERTIFICATE_INCORRECT("01"), 
	TAG_INCORRECT("02"), 
	USER_INCORRECT("03"),
	ORDER_INCORRECT("04"),
	
	INCORRECT_PARAM("12"),
	DEFAULT_ERROR ("11");
    
//	GIFT_CERTIFICATE_INCORRECT_ID("01"), 
//	GIFT_CERTIFICATE_INCORRECT_DATA("02"), 
//	TAG_INCORRECT_ID("03"), 
//	TAG_INCORRECT_DATA("04"),
//	INCORRECT_PARAM("12"),
//	DEFAULT_ERROR ("11");

	private final String errorCode;

	private ErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
