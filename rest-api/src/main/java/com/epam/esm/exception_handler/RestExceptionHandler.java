package com.epam.esm.exception_handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.epam.esm.exception.ErrorCode;
import com.epam.esm.exception.ErrorMessageKey;
import com.epam.esm.exception.InvalidDataException;
import com.epam.esm.exception.ParamException;
import com.epam.esm.exception.ResourceException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(ResourceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<IncorrectData> resourceExceptionHandler(ResourceException exception, 
			Locale locale) {
		String errorMessage = messageSource.getMessage(exception.getMessage(),
                new String[]{exception.getIncorrectParameter()}, locale);
		String errorCode = HttpStatus.NOT_FOUND.value() + exception.getErrorCode();
		IncorrectData incorrectData = new IncorrectData(errorMessage, errorCode);
		return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
	}
	
    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ResponseEntity<IncorrectData>> invalidDataExceptionHandler(InvalidDataException exception, 
    		Locale locale) {
        List <ResponseEntity<IncorrectData>> responseList = new ArrayList<>();
        List<String> errorKeysList = exception.getErrorMessageKeys();
        for (String errorKey : errorKeysList) {
        	String errorMessage = messageSource.getMessage(errorKey, 
        			new String[]{exception.getIncorrectParameter()}, locale);
        	String errorCode = HttpStatus.BAD_REQUEST.value() + exception.getErrorCode();
        	IncorrectData incorrectData = new IncorrectData(errorMessage, errorCode);
        	ResponseEntity<IncorrectData> responseEntity = 
        			new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
        	responseList.add(responseEntity);
        }
        return responseList;
    }

	@ExceptionHandler(ParamException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<IncorrectData> paramExceptionHandler(ParamException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(exception.getMessage(), new String[] {}, locale);
		String errorCode = HttpStatus.BAD_REQUEST.value() + exception.getErrorCode();
		IncorrectData incorrectData = new IncorrectData(errorMessage, errorCode);
		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
	}
    
//	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//	public ResponseEntity<IncorrectData> httpRequestMethodNotSupportedExceptionHandler(
//			HttpRequestMethodNotSupportedException exception, Locale locale) {
//		String errorMessage = messageSource.getMessage(
//				ErrorMessageKey.METHOD_NOT_ALLOWED.getErrorMessageKey(), new String[] {}, locale);
//		String errorCode = HttpStatus.METHOD_NOT_ALLOWED.value() + ErrorCode.DEFAULT_ERROR.getErrorCode();
//		IncorrectData incorrectData = new IncorrectData(errorMessage, errorCode);
//		return new ResponseEntity<>(incorrectData, HttpStatus.METHOD_NOT_ALLOWED);
//	}
	
//	@ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
//			BindException.class })
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public ResponseEntity<IncorrectData> incorectParamFormatExceptionHandler(
//			Exception exception, Locale locale) {
//		String errorMessage = messageSource.getMessage(ErrorMessageKey.INCORRECT_PARAM_FORMAT.getErrorMessageKey(),
//				new String[] {}, locale);
//		String errorCode = HttpStatus.BAD_REQUEST.value() + ErrorCode.DEFAULT_ERROR.getErrorCode();
//		IncorrectData incorrectData = new IncorrectData(errorMessage, errorCode);
//		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
//	}
	
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<IncorrectData> exceptionHandler(Exception exception, Locale locale) {
		String errorMessage = messageSource.getMessage(
				ErrorMessageKey.INTERNAL_SERVER_ERROR.getErrorMessageKey(), new String[] {}, locale);
		String errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value() + ErrorCode.DEFAULT_ERROR.getErrorCode();
		IncorrectData incorrectData = new IncorrectData(errorMessage, errorCode);
		return new ResponseEntity<>(incorrectData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
