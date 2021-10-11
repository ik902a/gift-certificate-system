package com.epam.esm.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.util.PaginationParamExtractor;

/**
 * The {@code TagDaoImpl} class works with tags table in database
 * 
 * @author Ihar Klepcha
 * @see TagDao
 */
@Repository
public class TagDaoImpl implements TagDao {
	private static final String FIND_ALL_TAGS = "FROM Tag";
	private static final String FIND_TAG_BY_NAME = "FROM Tag WHERE name=:name";
	private static final String DELETE_TAG = "DELETE FROM Tag WHERE id=:id";
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Tag create(Tag tag) {
		entityManager.persist(tag);
		return tag;
	}
    
    @Override
    public List<Tag> find(Map<String, String> params) {
    	int offset = PaginationParamExtractor.getOffset(params);
		int limit = PaginationParamExtractor.getLimit(params);
        return entityManager.createQuery(FIND_ALL_TAGS, Tag.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    
	@Override
	public Optional<Tag> findEntityById(long id) {
		return Optional.ofNullable(entityManager.find(Tag.class, id));
	}
	
	@Override
	public Optional<Tag> findEntityByName(String name) {
		return entityManager.createQuery(FIND_TAG_BY_NAME, Tag.class)
				.setParameter(ColumnName.TAGS_NAME, name)
				.getResultStream()
				.findFirst();
	}
	
	@Override
	public boolean delete(long id) {
	int row = entityManager.createQuery(DELETE_TAG)
	        .setParameter(ColumnName.TAGS_ID, id)
	        .executeUpdate();
	return row > 0;
	}

	@Override
	public List<Tag> findEntityByGiftCertificate(long giftCertificateId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public long getTotalNumber(Map<String, String> params) {
        return entityManager.createQuery(FIND_ALL_TAGS, Tag.class)
        		.getResultStream()
				.count();
	}
	
//	private static final String SQL_CREATE_TAG = "INSERT INTO tags (name) VALUES (?)";
//	private static final String SQL_FIND_ALL_TAGS = "SELECT id, name FROM tags";

//	private static final String SQL_DELETE_TAG = "DELETE FROM tags WHERE id=?";
//	private static final String SQL_FIND_TAG_BY_GIFT_CERTIFICATE = "SELECT id, name FROM tags WHERE id IN "
//			+ "(SELECT tag_id FROM gift_certificates_tags gct "
//			+ "JOIN gift_certificates gc ON gct.gift_certificate_id = gc.id WHERE gc.id=?)";	
}
