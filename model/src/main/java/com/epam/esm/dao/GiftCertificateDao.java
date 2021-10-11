package com.epam.esm.dao;

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
}
