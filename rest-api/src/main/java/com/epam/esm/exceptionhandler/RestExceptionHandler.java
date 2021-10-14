package com.epam.esm.exceptionhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.epam.esm.exception.ErrorCode;
import com.epam.esm.exception.ErrorMessageKey;
import com.epam.esm.exception.InvalidParamException;
//import com.epam.esm.exception.ParamException;
import com.epam.esm.exception.ResourceNotExistException;

/**
 * The {@code RestExceptionHandler} class represents controller which handle all
 * generated exceptions
 * 
 * @author Ihar Klepcha
 * @see ResponseEntityExceptionHandler
 */
@RestControllerAdvice
public class RestExceptionHandler {
//extends ResponseEntityExceptionHandler 
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Handles ResourceNotExistException
	 *
	 * @param exception {@link ResourceNotExistException} exception
	 * @param locale    {@link Locale} locale of HTTP request
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(ResourceNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorData> resourceExceptionHandler(ResourceNotExistException exception, 
			Locale locale) {
		String message = messageSource.getMessage(exception.getMessage(),
				new String[] { String.valueOf(exception.getIncorrectParameter()) }, locale);
		String code = HttpStatus.NOT_FOUND.value() + exception.getErrorCode();
		
		ErrorData incorrectData = new ErrorData(List.of(message), code);
		return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * Handles InvalidParamException
	 *
	 * @param exception {@link InvalidDataException} exception
	 * @param locale    {@link Locale} locale of HTTP request
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(InvalidParamException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorData> invalidParamExceptionHandler(InvalidParamException exception, 
			Locale locale) {
		List<String> messageList = new ArrayList<>();
		List<String> errorKeysList = exception.getErrorMessageKeys();
		for (String errorKey : errorKeysList) {
			String message = messageSource.getMessage(errorKey, new String[] {}, locale);
			messageList.add(message);
		}
		String code = HttpStatus.BAD_REQUEST.value() + exception.getErrorCode();
		ErrorData incorrectData = new ErrorData(messageList, code);
		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorData> methodExceptionHandler(MethodArgumentNotValidException exception) {
		List<String> messageList = new ArrayList<>();
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			String message = fieldError.getDefaultMessage();
			messageList.add(message);
		}
		String code = HttpStatus.BAD_REQUEST.value() + ErrorCode.INCORRECT_PARAM.getErrorCode();
		
		ErrorData incorrectData = new ErrorData(messageList, code);
		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorData> constraintExceptionHandler(ConstraintViolationException exception) {
		String message = exception.getLocalizedMessage();
		String code = HttpStatus.BAD_REQUEST.value() + ErrorCode.INCORRECT_PARAM.getErrorCode();
		
		ErrorData incorrectData = new ErrorData(List.of(message), code);
		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorData> argumentTypeMismatchExceptionHandler(
			MethodArgumentTypeMismatchException exception) {
		String message = exception.getLocalizedMessage();
		String code = HttpStatus.BAD_REQUEST.value() + ErrorCode.INCORRECT_PARAM.getErrorCode();
		
		ErrorData incorrectData = new ErrorData(List.of(message), code);
		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnsupportedOperationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorData> unsupportedOperationExceptionHandler(UnsupportedOperationException exception) {
		String message = exception.getLocalizedMessage();
		String code = HttpStatus.BAD_REQUEST.value() + ErrorCode.INCORRECT_PARAM.getErrorCode();
		ErrorData incorrectData = new ErrorData(List.of(message), code);
		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handles Exception
	 *
	 * @param exception {@link Exception} exception
	 * @param locale    {@link Locale} locale of HTTP request
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorData> exceptionHandler(Exception exception, Locale locale) {
		String errorMessage = messageSource.getMessage(ErrorMessageKey.INTERNAL_SERVER_ERROR.getErrorMessageKey(),
				new String[] {}, locale);
		String errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value() + ErrorCode.DEFAULT_ERROR.getErrorCode();
		
		ErrorData incorrectData = new ErrorData(List.of(errorMessage), errorCode);
		return new ResponseEntity<>(incorrectData, HttpStatus.INTERNAL_SERVER_ERROR);
	}
//
//	/**
//	 * Handles InvalidDataException
//	 *
//	 * @param exception {@link InvalidDataException} exception
//	 * @param locale    {@link Locale} locale of HTTP request
//	 * @return {@link ResponseEntity} the response message
//	 */
//	@ExceptionHandler(InvalidDataException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public List<ResponseEntity<IncorrectData>> invalidDataExceptionHandler(InvalidDataException exception, 
//			Locale locale) {
//		List<ResponseEntity<IncorrectData>> responseList = new ArrayList<>();
//		List<String> errorKeysList = exception.getErrorMessageKeys();
//		for (String errorKey : errorKeysList) {
//			String errorMessage = messageSource.getMessage(errorKey, 
//					new String[] { exception.getIncorrectParameter() },
//					locale);
//			String errorCode = HttpStatus.BAD_REQUEST.value() + exception.getErrorCode();
//			IncorrectData incorrectData = new IncorrectData(errorMessage, errorCode);
//			ResponseEntity<IncorrectData> responseEntity = new ResponseEntity<>(incorrectData, 
//					HttpStatus.BAD_REQUEST);
//			responseList.add(responseEntity);
//		}
//		return responseList;
//	}
//
//	/**
//	 * Handles ParamException
//	 *
//	 * @param exception {@link ParamException} exception
//	 * @param locale    {@link Locale} locale of HTTP request
//	 * @return {@link ResponseEntity} the response message
//	 */
//	@ExceptionHandler(ParamException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public ResponseEntity<IncorrectData> paramExceptionHandler(ParamException exception, Locale locale) {
//		String errorMessage = messageSource.getMessage(exception.getMessage(), new String[] {}, locale);
//		String errorCode = HttpStatus.BAD_REQUEST.value() + exception.getErrorCode();
//		IncorrectData incorrectData = new IncorrectData(errorMessage, errorCode);
//		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
//	}
}
