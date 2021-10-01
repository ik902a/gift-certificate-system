package com.epam.esm.service;

import java.util.List;
import java.util.Map;

import com.epam.esm.entity.GiftCertificate;

/**
 * The {@code GiftCertificateService} interface for operations with the gift
 * certificate
 * 
 * @author Ihar Klepcha
 */
public interface GiftCertificateService {

//	/**
//	 * Creates gift certificate in database
//	 * 
//	 * @param giftCertificate {@link GiftCertificate} gift certificate
//	 * @return {@link GiftCertificate} received from database
//	 */
//	GiftCertificate create(GiftCertificate giftCertificate);

	/**
	 * Finds gift certificate by parameters
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return {@link List} of {@link GiftCertificate} received from database
	 */
	List<GiftCertificate> find(Map<String, String> params);

//	/**
//	 * Finds gift certificate by id
//	 * 
//	 * @param id {@link long} gift certificate id
//	 * @return {@link GiftCertificate} received from database
//	 */
//	GiftCertificate findById(long id);
//
//	/**
//	 * Updates gift certificate
//	 * 
//	 * @param giftCertificate {@link GiftCertificate} gift certificate
//	 * @return {@link GiftCertificate} received from database
//	 */
//	GiftCertificate update(GiftCertificate giftCertificate);
//
//	/**
//	 * deletes gift certificate
//	 * 
//	 * @param id {@link long} gift certificate id
//	 */
//	void delete(long id);
}
