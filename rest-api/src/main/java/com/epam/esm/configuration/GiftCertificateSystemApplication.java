package com.epam.esm.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The {@code GiftCertificateSystemApplication} class for starting spring boot
 * rest api
 * 
 * @author Ihar Klepcha
 */
@SpringBootApplication(scanBasePackages = "com.epam.esm")
public class GiftCertificateSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftCertificateSystemApplication.class, args);
	}
}
