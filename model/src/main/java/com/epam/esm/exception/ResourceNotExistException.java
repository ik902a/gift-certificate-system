package com.epam.esm.exception;

/**
 * The {@code ResourceNotExistException} class describes the exception
 * 
 * @author Ihar Klepcha
 * @see GiftCertificateSystemException
 */
public class ResourceNotExistException extends GiftCertificateSystemException {
	private static final long serialVersionUID = 1L;
	private String incorrectParameter;
	private String errorCode;

	/**
	 * Constructs a new resource exception
	 */
	public ResourceNotExistException() {
		super();
	}

	/**
	 * Constructs a new resource exception with the specified
	 * 
	 * @param message            {@link String} message
	 * @param incorrectParameter {@link String} incorrect value
	 * @param errorCode          {@link String} custom code error
	 */
	public ResourceNotExistException(String message, String incorrectParameter, String errorCode) {
		super(message);
		this.incorrectParameter = incorrectParameter;
		this.errorCode = errorCode;
	}

	/**
	 * Constructs a new resource exception with the specified
	 * 
	 * @param message   {@link String} message
	 * @param errorCode {@link String} custom code error
	 */
	public ResourceNotExistException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Constructs a new resource exception with the specified
	 * 
	 * @param message            {@link String} message
	 * @param cause              {@link Throwable} cause
	 * @param incorrectParameter {@link String} incorrect value
	 * @param errorCode          {@link String} custom code error
	 */
	public ResourceNotExistException(String message, Throwable cause, String incorrectParameter, String errorCode) {
		super(message, cause);
		this.incorrectParameter = incorrectParameter;
		this.errorCode = errorCode;
	}

	/**
	 * Constructs a new resource exception with the specified
	 * 
	 * @param cause              {@link Throwable} cause
	 * @param incorrectParameter {@link String} incorrect value
	 * @param errorCode          {@link String} custom code error
	 */
	public ResourceNotExistException(Throwable cause, String incorrectParameter, String errorCode) {
		super(cause);
		this.incorrectParameter = incorrectParameter;
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
