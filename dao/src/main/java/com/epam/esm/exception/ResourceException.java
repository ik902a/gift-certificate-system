package com.epam.esm.exception;

public class ResourceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private String incorrectParameter;
    private String errorCode;

	public ResourceException() {
		super();
	}

	public ResourceException(String message) {
		super(message);
	}

	public ResourceException(String message, String incorrectParameter, String errorCode) {
		super(message);
		this.incorrectParameter = incorrectParameter;
		this.errorCode = errorCode;
	}

	public ResourceException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public String getIncorrectParameter() {
		return incorrectParameter;
	}

	public void setIncorrectParameter(String incorrectParameter) {
		this.incorrectParameter = incorrectParameter;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
