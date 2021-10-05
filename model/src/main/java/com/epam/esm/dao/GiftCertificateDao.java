package com.epam.esm.dao;

import java.util.List;
import java.util.Map;

import com.epam.esm.entity.GiftCertificate;

/**
 * The {@code GiftCertificateDao} interface works with gift_certificates table in database
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface GiftCertificateDao extends BaseDao<GiftCertificate> {
	
	/**
	 * Looks for all entity
	 * 
	 * @param  params {@link Map} of {@link String} and {@link String} parameters for searching
	 * @return {@link List} of {@link T} entity received from database
	 */
	List<GiftCertificate> find(Map<String, String> params);

	/**
	 * Updates an entity 
	 * 
	 * @param  giftCertificate {@link GiftCertificate} entity
	 * @return {@link GiftCertificate} entity received from database
	 */
	GiftCertificate update(GiftCertificate giftCertificate);

//	/**
//	 * Creates a record in the gift_certificates _tag table in database
//	 * 
//	 * @param giftCertificate {@link GiftCertificate} entity
//	 */
//	void createGiftCertificateTag(GiftCertificate giftCertificate);

	/**
	 * Deletes a record in the gift_certificates _tag table in database
	 * 
	 * @param id {@link long} entity id
	 * @return boolean true if the record has been deleted, else false
	 */
	boolean deleteGiftCertificateTag(long id);
}
