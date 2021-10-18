package com.epam.esm.service;

import java.util.Map;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.PageDto;

/**
 * The {@code GiftCertificateService} interface for operations with the gift
 * certificate
 * 
 * @author Ihar Klepcha
 */
public interface GiftCertificateService {

	/**
	 * Creates gift certificate in database
	 * 
	 * @param giftCertificate {@link GiftCertificateDto} gift certificate
	 * @return {@link GiftCertificateDto} received from database
	 */
	GiftCertificateDto create(GiftCertificateDto giftCertificateDto);

	/**
	 * Finds gift certificate by parameters
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return {@link PageDto} of {@link GiftCertificateDto} received from database
	 */
	PageDto<GiftCertificateDto> find(Map<String, String> params);

	/**
	 * Finds gift certificate by id
	 * 
	 * @param id is gift certificate id
	 * @return {@link GiftCertificateDto} received from database
	 */
	GiftCertificateDto findById(long id);

	/**
	 * Updates gift certificate
	 * 
	 * @param giftCertificate {@link GiftCertificateDto} gift certificate
	 * @return {@link GiftCertificateDto} received from database
	 */
	GiftCertificateDto update(GiftCertificateDto giftCertificateDto);

	/**
	 * Deletes gift certificate
	 * 
	 * @param id is gift certificate id
	 */
	void delete(long id);
}
