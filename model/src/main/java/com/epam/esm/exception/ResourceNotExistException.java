package com.epam.esm.exception;

/**
 * The {@code ResourceNotExistException} class describes the exception
 * 
 * @author Ihar Klepcha
 * @see RuntimeException
 */
public class ResourceNotExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private long incorrectParameter;
    private String errorCode;

	public ResourceNotExistException() {
		super();
	}

	public ResourceNotExistException(String message, long incorrectParameter, String errorCode) {
		super(message);
		this.incorrectParameter = incorrectParameter;
		this.errorCode = errorCode;
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	public long getIncorrectParameter() {
		return incorrectParameter;
	}

	public void setIncorrectParameter(long incorrectParameter) {
		this.incorrectParameter = incorrectParameter;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
