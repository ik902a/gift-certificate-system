package com.epam.esm.exceptionhandler;

import java.util.List;

/**
 * The {@code IncorrectData} class describes the entity error data for response
 * 
 * @author Ihar Klepcha
 */
public class ErrorData {
    private List<String> errorMessage;
    private String errorCode;
    
	/**
	 * Constructs a new error data
	 */
	public ErrorData() {
		super();
	}
	
	/**
	 * Constructs a new error date with the specified
	 * 
	 * @param errorMessage {@link String} error message
	 * @param errorCode {@link String} error code
	 */
	public ErrorData(List<String> errorMessage, String errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public List<String> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
