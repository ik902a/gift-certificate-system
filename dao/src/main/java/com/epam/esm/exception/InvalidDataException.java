package com.epam.esm.exception;

import java.util.List;

public class InvalidDataException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private List<String> errorMessageKeys;
    private String incorrectParameter;
    private String errorCode;

	public InvalidDataException(List<String> errorMessageKeys, String incorrectParameter, String errorCode) {
		super();
		this.errorMessageKeys = errorMessageKeys;
		this.incorrectParameter = incorrectParameter;
		this.errorCode = errorCode;
	}
	
	public List<String> getErrorMessageKeys() {
		return errorMessageKeys;
	}

	public String getIncorrectParameter() {
		return incorrectParameter;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
