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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.epam.esm.exception.ErrorCode;
import com.epam.esm.exception.ErrorMessageKey;
//import com.epam.esm.exception.InvalidDataException;
//import com.epam.esm.exception.ParamException;
//import com.epam.esm.exception.ResourceNotExistException;

/**
 * The {@code RestExceptionHandler} class represents controller which handle all
 * generated exceptions
 * 
 * @author Ihar Klepcha
 * @see ResponseEntityExceptionHandler
 */
@RestControllerAdvice
public class RestExceptionHandler 
//extends ResponseEntityExceptionHandler 
{
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<IncorrectData> methodExceptionHandler(MethodArgumentNotValidException exception) {
		List<String> messageList = new ArrayList<>();
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			
			String message = fieldError.getDefaultMessage();
			messageList.add(message);
		}
		String code = HttpStatus.BAD_REQUEST.value() + ErrorCode.INCORRECT_PARAM.getErrorCode();
		IncorrectData incorrectData = new IncorrectData(messageList, code);

		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<IncorrectData> constraintExceptionHandler(ConstraintViolationException exception) {
		String message = exception.getLocalizedMessage();
		String code = HttpStatus.BAD_REQUEST.value() + ErrorCode.INCORRECT_PARAM.getErrorCode();

		IncorrectData incorrectData = new IncorrectData(List.of(message), code);

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
	public ResponseEntity<IncorrectData> exceptionHandler(Exception exception, Locale locale) {
		String errorMessage = messageSource.getMessage(ErrorMessageKey.INTERNAL_SERVER_ERROR.getErrorMessageKey(),
				new String[] {}, locale);
		String errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value() + ErrorCode.DEFAULT_ERROR.getErrorCode();
		IncorrectData incorrectData = new IncorrectData(List.of(errorMessage), errorCode);
		return new ResponseEntity<>(incorrectData, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	/**
//	 * Handles ResourceNotExistException
//	 *
//	 * @param exception {@link ResourceNotExistException} exception
//	 * @param locale    {@link Locale} locale of HTTP request
//	 * @return {@link ResponseEntity} the response message
//	 */
//	@ExceptionHandler(ResourceNotExistException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<IncorrectData> resourceExceptionHandler(ResourceNotExistException exception, 
//			Locale locale) {
//		String errorMessage = messageSource.getMessage(exception.getMessage(),
//				new String[] { exception.getIncorrectParameter() }, locale);
//		String errorCode = HttpStatus.NOT_FOUND.value() + exception.getErrorCode();
//		IncorrectData incorrectData = new IncorrectData(errorMessage, errorCode);
//		return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
//	}
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
