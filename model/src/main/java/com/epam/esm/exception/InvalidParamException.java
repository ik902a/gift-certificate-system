package com.epam.esm.exception;

import java.util.List;

/**
 * The {@code InvalidParamException} class describes the exception
 * 
 * @author Ihar Klepcha
 * @see GiftCertificateSystemException
 */
public class InvalidParamException extends GiftCertificateSystemException {
	private static final long serialVersionUID = 1L;
	private List<String> errorMessageKeys;
	private String incorrectParameter;
	private String errorCode;

	/**
	 * Constructs a new invalid parameter exception
	 */
	public InvalidParamException() {
		super();
	}

	/**
	 * Constructs a new invalid exception with the specified
	 * 
	 * @param errorMessageKeys   {@link List} of {@link String} keys for message
	 * @param incorrectParameter {@link String} incorrect value
	 * @param errorCode          {@link String} custom code error
	 */
	public InvalidParamException(List<String> errorMessageKeys, String incorrectParameter, String errorCode) {
		super();
		this.errorMessageKeys = errorMessageKeys;
		this.incorrectParameter = incorrectParameter;
		this.errorCode = errorCode;
	}

	/**
	 * Constructs a new invalid exception with the specified
	 * 
	 * @param errorMessageKeys {@link List} of{@link String} keys for message
	 * @param errorCode        {@link String} custom code error
	 */
	public InvalidParamException(List<String> errorMessageKeys, String errorCode) {
		super();
		this.errorMessageKeys = errorMessageKeys;
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
