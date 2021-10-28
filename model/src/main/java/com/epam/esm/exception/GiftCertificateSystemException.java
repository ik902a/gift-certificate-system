package com.epam.esm.exception;

/**
 * The {@code GiftCertificateSystemException} class is the root abstract class in the exception hierarchy
 * 
 * @author Ihar Klepcha
 * @see RuntimeException
 */
public class GiftCertificateSystemException extends RuntimeException {
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

	/**
	 * Constructor for organization of inheritance exception with the specified
	 * 
	 * @param message {@link String} message
	 * @param cause {@link Throwable} cause
	 */
	public GiftCertificateSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for organization of inheritance exception with the specified
	 * 
	 * @param cause {@link Throwable} cause
	 */
	public GiftCertificateSystemException(Throwable cause) {
		super(cause);
	}
}
