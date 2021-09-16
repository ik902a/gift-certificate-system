package com.epam.esm.exception_handler;

public class IncorrectData {

    private String errorMessage;
    private String errorCode;
    
	public IncorrectData() {
		super();
	}
	
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
