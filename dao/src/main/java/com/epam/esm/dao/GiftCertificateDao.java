package com.epam.esm.dao;

import java.util.List;

import com.epam.esm.entity.GiftCertificate;

/**
 * The {@code GiftCertificateDao} interface works with gift_certificates table in database
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface GiftCertificateDao extends BaseDao<GiftCertificate> {

	/**
	 * Updates an entity 
	 * 
	 * @param  giftCertificate {@link GiftCertificate} entity
	 * @return {@link GiftCertificate} entity received from database
	 */
	GiftCertificate update(GiftCertificate giftCertificate);

	/**
	 * Creates a record in the gift_certificates _tag table in database
	 * 
	 * @param giftCertificate {@link GiftCertificate} entity
	 */
	void createGiftCertificateTag(GiftCertificate giftCertificate);

	/**
	 * Deletes a record in the gift_certificates _tag table in database
	 * 
	 * @param id {@link long} entity id
	 * @return boolean true if the record has been deleted, else false
	 */
	boolean deleteGiftCertificateTag(long id);

	/**
	 * Looks for an entity where tag has name
	 * 
	 * @param tagName {@link String} name of tag
	 * @param orderBy {@link String} order
	 * @return {@link List} of {@link T} entity received from database
	 */
	List<GiftCertificate> findEntityByTagName(String tagName, String orderBy);
	
	/**
	 * Looks for an entity where entity has name
	 * 
	 * @param name {@link String}  entity name
	 * @param orderBy {@link String} order
	 * @return {@link List} of {@link T} entity received from database
	 */
	List<GiftCertificate> findEntityByPartName(String name, String orderBy);
	
	/**
	 * Looks for an entity where entity has description
	 * 
	 * @param name {@link String}  entity description
	 * @param orderBy {@link String} order
	 * @return {@link List} of {@link T} entity received from database
	 */
	List<GiftCertificate> findEntityByPartDescription(String description, String orderBy);
}
