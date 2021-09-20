package com.epam.esm.configuration;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * The {@code ModelConfiguration} class contains spring configuration
 * 
 * @author Ihar Klepcha
 */
@Configuration
@ComponentScan("com.epam.esm.dao.impl")
public class TestConfiguration {
	public static Logger log = LogManager.getLogger();
	@Value("classpath:gift_certificates_db_script.sql")
	private String createTableScript;
	@Value("classpath:init_db_script.sql")
	private String initTableScript;
	
    /**
     * Creates bean embeddedDataSource for working with database by testing
     *
     * @return the data source
     */
	@Bean(destroyMethod = "shutdown")
	@Profile("dev")
	public DataSource embeddedDataSource() {
		log.info("registration embedded data source Bean");
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript(createTableScript)
				.addScript(initTableScript).build();
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
