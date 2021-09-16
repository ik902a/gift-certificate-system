package com.epam.esm.dao;

import java.util.List;

import com.epam.esm.entity.Tag;

public interface TagDao extends BaseDao<Tag> {
	
	List<Tag> findEntityByGiftCertificate (long giftCertificateId);

}
