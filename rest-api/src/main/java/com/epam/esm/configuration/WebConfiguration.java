package com.epam.esm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The {@code WebConfiguration} class contains spring configuration
 * 
 * @author Ihar Klepcha
 * @see WebMvcConfigurer
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	private String basename;
	private String encoding;

	/**
	 * Constructs a web configuration
	 * 
	 * @param basename {@link String} basename for resource
	 * @param encoding {@link String} encoding
	 */
	@Autowired
	public WebConfiguration(@Value("messages") String basename, @Value("UTF-8") String encoding) {
		super();
		this.basename = basename;
		this.encoding = encoding;
	}

	/**
	 * Creates bean MessageSource for working
	 *
	 * @return the message source
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(basename);
		messageSource.setDefaultEncoding(encoding);
		return messageSource;
	}
}
