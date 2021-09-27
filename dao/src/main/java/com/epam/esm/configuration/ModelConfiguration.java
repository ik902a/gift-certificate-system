package com.epam.esm.configuration;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * The {@code ModelConfiguration} class contains spring configuration
 * 
 * @author Ihar Klepcha
 */
@Configuration
@PropertySource("classpath:database.properties")
public class ModelConfiguration {
	public static Logger log = LogManager.getLogger();
	@Value("${db.driver}")
	private String driverClass;
	@Value("${db.user}")
	private String user;
	@Value("${db.password}")
	private String password;
	@Value("${db.url}")
	private String url;

    /**
     * Creates bean DataSource for working with database
     *
     * @return the data source
     * @throws RuntimeException
     */
	@Bean
	@Profile("prod")
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(driverClass);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(user);
			dataSource.setPassword(password);
		} catch (PropertyVetoException e) {
			log.fatal("ERROR driver doesn't found", e);
			throw new RuntimeException("driver doesn't found", e);
		}
		log.info("registration data source Bean");
		return dataSource;
	}

    /**
     * Creates bean JdbcTemplate for executing queries to database
     *
     * @param dataSource {@link DataSource} the data source
     * @return {@link JdbcTemplate} the jdbc template
     */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		log.info("registration JDBC template Bean");
		return new JdbcTemplate(dataSource);
	}
}
