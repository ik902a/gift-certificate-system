package com.epam.esm.exception;

public enum ErrorCode {

	GIFT_CERTIFICATE_INCORRECT_ID("01"), 
	GIFT_CERTIFICATE_INCORRECT_DATA("02"), 
	TAG_INCORRECT_ID("03"), 
	TAG_INCORRECT_DATA("04"),
	INCORRECT_PARAM("12"),
	DEFAULT_ERROR ("11");

	private final String errorCode;

	private ErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
