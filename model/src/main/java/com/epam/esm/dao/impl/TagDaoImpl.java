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
	private static final String FIND_MOST_POPULAR_TAG = "SELECT t.id, t.name FROM tags t " +
            "JOIN gift_certificates_tags gct ON t.id=gct.tag_id " +
            "JOIN gift_certificates gc ON gct.gift_certificate_id=gc.id " +
            "JOIN gift_certificates_orders gco ON gc.id=gco.gift_certificate_id " +
            "JOIN orders o ON gco.order_id=o.id WHERE o.user_id=" +
            "(SELECT user_id FROM orders GROUP BY user_id ORDER BY sum(cost) DESC LIMIT 1) " +
            "GROUP BY t.id ORDER BY count(*) DESC LIMIT 1";
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
	public long getTotalNumber(Map<String, String> params) {
        return entityManager.createQuery(FIND_ALL_TAGS, Tag.class)
        		.getResultStream()
				.count();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<Tag> findMostPopularTagOfUserWithHighestCostOfAllOrders() {
		return entityManager.createNativeQuery(FIND_MOST_POPULAR_TAG, Tag.class)
                .getResultStream()
                .findFirst();
	}
}
