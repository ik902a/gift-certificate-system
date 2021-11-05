package com.epam.esm.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The {@code ModelConfiguration} class contains spring configuration
 * 
 * @author Ihar Klepcha
 */
@SpringBootApplication(scanBasePackages = "com.epam.esm")
public class TestConfiguration {
	public static Logger log = LogManager.getLogger();
}
