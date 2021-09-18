package com.epam.esm.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * The {@code WebInitializer} class initializers dispatcher servlet
 * 
 * @author Ihar Klepcha
 * @see AbstractAnnotationConfigDispatcherServletInitializer
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	private static final String ACTIVE_PROFILE_PARAM = "spring.profiles.active";
	private static final String PROD_PROFILE = "prod";

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.setInitParameter(ACTIVE_PROFILE_PARAM, PROD_PROFILE);
	}
}
