package com.epam.esm.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import com.epam.esm.exception.ErrorCode;
import com.epam.esm.exception.ErrorMessageKey;
import com.epam.esm.exceptionhandler.ErrorData;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The {@code AuthEntryPointJwt} class is for generate exception reponse
 *
 * @author Ihar Klepcha
 * @see AuthenticationEntryPoint
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
	public static Logger log = LogManager.getLogger();
	private static final String ENCODING = "UTF-8";
	@Autowired
	private MessageSource messageSource;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.error(authException.getLocalizedMessage(), authException);
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
		String message = messageSource.getMessage(ErrorMessageKey.NO_ACCESS, new String[] {}, request.getLocale());;
	    response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
	    response.setCharacterEncoding(ENCODING);
	    response.setStatus(HttpStatus.UNAUTHORIZED.value());
	    response.getWriter()
	    	.write(String.valueOf(new ObjectMapper()
	                    .writeValueAsString(new ErrorData(List.of(message),
	                    		HttpStatus.UNAUTHORIZED.value() + ErrorCode.UNAUTHORIZED))));
	}
}

