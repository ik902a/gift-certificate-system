package com.epam.esm.configuration;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.jdbc.core.JdbcTemplate;

//import com.mchange.v2.c3p0.ComboPooledDataSource;

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
	
	
	
	
	
	
	
	
	
	
	
	
	


//    /**
//     * Creates bean DataSource for working with database
//     *
//     * @return the data source
//     * @throws RuntimeException
//     */
//	@Bean
//	@Profile("prod")
//	public DataSource dataSource() {
//		ComboPooledDataSource dataSource = new ComboPooledDataSource();
//		try {
//			dataSource.setDriverClass(driverClass);
//			dataSource.setJdbcUrl(url);
//			dataSource.setUser(user);
//			dataSource.setPassword(password);
//		} catch (PropertyVetoException e) {
//			log.fatal("ERROR driver doesn't found", e);
//			throw new RuntimeException("driver doesn't found", e);
//		}
//		log.info("registration data source Bean");
//		return dataSource;
//	}
//
//    /**
//     * Creates bean JdbcTemplate for executing queries to database
//     *
//     * @param dataSource {@link DataSource} the data source
//     * @return {@link JdbcTemplate} the jdbc template
//     */
//	@Bean
//	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//		log.info("registration JDBC template Bean");
//		return new JdbcTemplate(dataSource);
//	}
}
