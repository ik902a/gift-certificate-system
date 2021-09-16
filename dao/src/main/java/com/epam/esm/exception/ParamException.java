package com.epam.esm.exception;

public class ParamException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private String errorCode;
    
	public ParamException() {
		super();
	}

	public ParamException(String message) {
		super(message);

	}

	public ParamException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public String getErrorCode() {
		return errorCode;
	}
}
