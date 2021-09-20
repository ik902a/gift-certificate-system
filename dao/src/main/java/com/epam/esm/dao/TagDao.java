package com.epam.esm.dao;

import java.util.List;

import com.epam.esm.entity.Tag;

/**
 * The {@code TagDao} interface works with tags table in database
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface TagDao extends BaseDao<Tag> {
	
	/**
	 * Looks for all entity
	 * 
	 * @return {@link List} of {@link Tag} entity received from database
	 */
	List<Tag> findAll();

	/**
	 * Looks for an entity where entity has name
	 * 
	 * @param giftCertificateId {@link long} gift certificate id
	 * @return {@link List} of {@link Tag} entity received from database
	 */
	List<Tag> findEntityByGiftCertificate(long giftCertificateId);
}
