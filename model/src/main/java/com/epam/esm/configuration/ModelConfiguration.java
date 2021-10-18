package com.epam.esm.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The {@code ModelConfiguration} class contains spring configuration
 * 
 * @author Ihar Klepcha
 */
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "com.epam.esm")
@EntityScan(basePackages = "com.epam.esm")
public class ModelConfiguration {
	public static Logger log = LogManager.getLogger();

	/**
	 * Creates beam ModelMapper for mapping entity to dto and opposite
	 *
	 * @return the model mapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
			.setMatchingStrategy(MatchingStrategies.STRICT)
			.setFieldMatchingEnabled(true)
			.setSkipNullEnabled(true)
			.setFieldAccessLevel(AccessLevel.PRIVATE);
		return mapper;
	}
}
