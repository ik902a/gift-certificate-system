package com.epam.esm.exception;

/**
 * The {@code ResourceNotExistException} class describes the exception
 * 
 * @author Ihar Klepcha
 * @see RuntimeException
 */
public class ResourceNotExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private String incorrectParameter;
    private String errorCode;

	public ResourceNotExistException() {
		super();
	}

	public ResourceNotExistException(String message) {
		super(message);
	}

	public ResourceNotExistException(String message, String incorrectParameter, String errorCode) {
		super(message);
		this.incorrectParameter = incorrectParameter;
		this.errorCode = errorCode;
	}

	public ResourceNotExistException(String message, String errorCode) {
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
