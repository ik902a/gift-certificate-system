package com.epam.esm.exception;

/**
 * The {@code GiftCertificateSystemException} class is the root abstract class in the exception hierarchy
 * 
 * @author Ihar Klepcha
 * @see RuntimeException
 */
public abstract class GiftCertificateSystemException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for organization of inheritance exception
	 */
	public GiftCertificateSystemException() {
		super();
	}

	/**
	 * Constructor for organization of inheritance exception with the specified
	 * 
	 * @param message {@link String} message
	 */
	public GiftCertificateSystemException(String message) {
		super(message);
	}
}
