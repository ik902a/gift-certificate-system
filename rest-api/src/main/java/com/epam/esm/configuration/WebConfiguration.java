package com.epam.esm.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * The {@code WebConfiguration} class contains spring configuration
 * 
 * @author Ihar Klepcha
 * @see WebMvcConfigurer
 */
//@Configuration
//@EnableWebMvc
//@ComponentScan("com.epam.esm")
public class WebConfiguration implements WebMvcConfigurer {
	@Value("messages")
	private String basename;
	@Value("UTF-8")
	private String encoding;

//	/**
//	 * Creates bean MessageSource for working
//	 *
//	 * @return the message source
//	 */
//	@Bean
//	public MessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename(basename);
//		messageSource.setDefaultEncoding(encoding);
//		return messageSource;
//	}
//
//	/**
//	 * Creates bean LocaleResolver for working
//	 *
//	 * @return the resolver
//	 */
//	@Bean
//	public LocaleResolver localeResolver() {
//		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
//		localeResolver.setDefaultLocale(Locale.US);
//		return localeResolver;
//	}
}
