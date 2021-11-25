package com.epam.esm.exceptionhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.LockedException;

import com.epam.esm.exception.ErrorCode;
import com.epam.esm.exception.ErrorMessageKey;
import com.epam.esm.exception.InvalidParamException;
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
	public static Logger log = LogManager.getLogger();
	private static final String CONNECTOR = ": ";
	private MessageSource messageSource;

	/**
	 * Constructs a REST exception handler
	 * 
	 * @param messageSource {@link MessageSource} source of messages
	 */
	@Autowired
	public RestExceptionHandler(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	/**
	 * Handles ResourceNotExistException
	 *
	 * @param exception {@link ResourceNotExistException} exception
	 * @param locale    {@link Locale} locale of HTTP request
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(ResourceNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorData> resourceExceptionHandler(ResourceNotExistException exception, Locale locale) {
		log.error(exception.getLocalizedMessage(), exception);
		String message = messageSource.getMessage(exception.getMessage(),
				new String[] { exception.getIncorrectParameter() }, locale);
		String code = HttpStatus.NOT_FOUND.value() + exception.getErrorCode();
		ErrorData incorrectData = new ErrorData(List.of(message), code);
		return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles InvalidParamException
	 *
	 * @param exception {@link InvalidParamException} exception
	 * @param locale    {@link Locale} locale of HTTP request
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(InvalidParamException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorData> invalidParamExceptionHandler(InvalidParamException exception, Locale locale) {
		log.error(exception.getLocalizedMessage(), exception);
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

	/**
	 * Handles MethodArgumentNotValidException
	 *
	 * @param exception {@link MethodArgumentNotValidException} exception
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorData> methodExceptionHandler(MethodArgumentNotValidException exception) {
		log.error(exception.getLocalizedMessage(), exception);
		List<String> messageList = new ArrayList<>();
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			String message = fieldError.getField() + CONNECTOR + fieldError.getDefaultMessage();
			messageList.add(message);
		}
		String code = HttpStatus.BAD_REQUEST.value() + ErrorCode.INCORRECT_PARAM;
		ErrorData incorrectData = new ErrorData(messageList, code);
		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles ConstraintViolationException
	 *
	 * @param exception {@link ConstraintViolationException} exception
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorData> constraintExceptionHandler(ConstraintViolationException exception) {
		log.error(exception.getLocalizedMessage(), exception);
		String message = exception.getLocalizedMessage();
		String code = HttpStatus.BAD_REQUEST.value() + ErrorCode.INCORRECT_PARAM;
		ErrorData incorrectData = new ErrorData(List.of(message), code);
		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles BadCredentialsException
	 *
	 * @param exception {@link BadCredentialsException} exception
	 * @param locale    {@link Locale} locale of HTTP request
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ErrorData> badCredentialsExceptionHandler(BadCredentialsException exception, Locale locale) {
		log.error(exception.getLocalizedMessage(), exception);
		String message = messageSource.getMessage(ErrorMessageKey.BAD_CREDENTIALS, new String[] {}, locale);
		String code = HttpStatus.UNAUTHORIZED.value() + ErrorCode.UNAUTHORIZED;
		ErrorData incorrectData = new ErrorData(List.of(message), code);
		return new ResponseEntity<>(incorrectData, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Handles AccessDeniedException
	 *
	 * @param exception {@link AccessDeniedException} exception
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ErrorData> accessDeniedExceptionHandler(AccessDeniedException exception) {
		log.error(exception.getLocalizedMessage(), exception);
		String message = exception.getLocalizedMessage();
		String code = HttpStatus.FORBIDDEN.value() + ErrorCode.FORBIDDEN;
		ErrorData incorrectData = new ErrorData(List.of(message), code);
		return new ResponseEntity<>(incorrectData, HttpStatus.FORBIDDEN);
	}

	/**
	 * Handles LockedException
	 *
	 * @param exception {@link LockedException} exception
	 * @param locale    {@link Locale} locale of HTTP request
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(LockedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ErrorData> lockedExceptionHandler(LockedException exception, Locale locale) {
		log.error(exception.getLocalizedMessage(), exception);
		String code = HttpStatus.UNAUTHORIZED.value() + ErrorCode.UNAUTHORIZED;
		String message = messageSource.getMessage(ErrorMessageKey.LOCKED_ACCOUNT, new String[] {}, locale);
		ErrorData incorrectData = new ErrorData(List.of(message), code);
		return new ResponseEntity<>(incorrectData, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Handles MethodArgumentTypeMismatchException and
	 * HttpMessageNotReadableException
	 *
	 * @param exception {@link NestedRuntimeException} exception
	 * @param locale    {@link Locale} locale of HTTP request
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorData> argumentTypeMismatchExceptionHandler(NestedRuntimeException exception,
			Locale locale) {
		log.error(exception.getLocalizedMessage(), exception);
		String message = messageSource.getMessage(ErrorMessageKey.INCORRECT_VALUE_TYPE, new String[] {}, locale);
		String code = HttpStatus.BAD_REQUEST.value() + ErrorCode.INCORRECT_PARAM;
		ErrorData incorrectData = new ErrorData(List.of(message), code);
		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles DataIntegrityViolationException
	 *
	 * @param exception {@link DataIntegrityViolationException} exception
	 * @param locale    {@link Locale} locale of HTTP request
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorData> dataViolationExceptionHandler(DataIntegrityViolationException exception,
			Locale locale) {
		log.error(exception.getLocalizedMessage(), exception);
		String message = messageSource.getMessage(ErrorMessageKey.DATABASE_ERROR, new String[] {}, locale);
		String code = HttpStatus.BAD_REQUEST.value() + ErrorCode.INCORRECT_PARAM;
		ErrorData incorrectData = new ErrorData(List.of(message), code);
		return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles UnsupportedOperationException
	 *
	 * @param exception {@link UnsupportedOperationException} exception
	 * @return {@link ResponseEntity} the response message
	 */
	@ExceptionHandler(UnsupportedOperationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorData> unsupportedOperationExceptionHandler(UnsupportedOperationException exception) {
		log.error(exception.getLocalizedMessage(), exception);
		String message = exception.getLocalizedMessage();
		String code = HttpStatus.BAD_REQUEST.value() + ErrorCode.INCORRECT_PARAM;
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
		log.error(exception.getMessage(), exception);
		String message = messageSource.getMessage(ErrorMessageKey.INTERNAL_SERVER_ERROR, new String[] {}, locale);
		String errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value() + ErrorCode.DEFAULT_ERROR;
		ErrorData incorrectData = new ErrorData(List.of(message), errorCode);
		return new ResponseEntity<>(incorrectData, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
