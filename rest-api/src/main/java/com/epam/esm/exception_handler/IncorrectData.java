package com.epam.esm.exception_handler;

/**
 * The {@code IncorrectData} class describes the entity incorrect data for response
 * 
 * @author Ihar Klepcha

 */
public class IncorrectData {
    private String errorMessage;
    private String errorCode;
    
	/**
	 * Constructs a new incorrect data
	 */
	public IncorrectData() {
		super();
	}
	
	/**
	 * Constructs a new incorrect date with the specified
	 * 
	 * @param errorMessage {@link String} error message
	 * @param errorCode {@link String} error code
	 */
	public IncorrectData(String errorMessage, String errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
