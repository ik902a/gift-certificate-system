package com.epam.esm.dao;

import java.util.List;

import com.epam.esm.entity.GiftCertificate;

public interface GiftCertificateDao extends BaseDao<GiftCertificate> {

	GiftCertificate update(GiftCertificate giftCertificate);

	void createGiftCertificateTag(GiftCertificate giftCertificate);

	boolean deleteGiftCertificateTag(long id);

	List<GiftCertificate> findEntityByTagName(String tagName, String orderBy);
	
	List<GiftCertificate> findEntityByPartName(String name, String orderBy);
	
	List<GiftCertificate> findEntityByPartDescription(String description, String orderBy);
}
