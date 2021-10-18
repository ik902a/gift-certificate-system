package com.epam.esm.exception;

/**
 * The {@code ResourceNotExistException} class describes the exception
 * 
 * @author Ihar Klepcha
 * @see GiftCertificateSystemException
 */
public class ResourceNotExistException extends GiftCertificateSystemException {
	private static final long serialVersionUID = 1L;
	private long incorrectParameter;
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
	 * @param message {@link String} message
	 * @param incorrectParameter is incorrect value
	 * @param errorCode {@link String} custom code error
	 */
	public ResourceNotExistException(String message, long incorrectParameter, String errorCode) {
		super(message);
		this.incorrectParameter = incorrectParameter;
		this.errorCode = errorCode;
	}
	
	/**
	 * Constructs a new resource exception with the specified
	 * 
	 * @param incorrectParameter is incorrect value
	 * @param errorCode {@link String} custom code error
	 */
	public ResourceNotExistException(String message, String errorCode) {
		super(message);
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
