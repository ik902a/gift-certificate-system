package com.epam.esm.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.util.GiftCertificateQueryBuilder;
import com.epam.esm.util.PaginationParamExtractor;
import com.epam.esm.entity.GiftCertificate;

/**
 * The {@code GiftCertificateDaoImpl} class works with gift_certificates table in database
 * 
 * @author Ihar Klepcha
 * @see GiftCertificateDao
 */
@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
	public static Logger log = LogManager.getLogger();
	private static final String FIND_GIFT_CERTIFICATE_BY_NAME = "FROM GiftCertificate WHERE name=:name";
	private static final String DELETE_GIFT_CERTIFICATE = "DELETE GiftCertificate WHERE id = :id";
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public GiftCertificate create(GiftCertificate giftCertificate) {
		entityManager.persist(giftCertificate);
		return giftCertificate;
	}

	@Override
	public List<GiftCertificate> find(Map<String, String> params) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GiftCertificate> criteriaQuery = GiftCertificateQueryBuilder.buildQuery(params, criteriaBuilder);
		int offset = PaginationParamExtractor.getOffset(params);
		int limit = PaginationParamExtractor.getLimit(params);
		return entityManager.createQuery(criteriaQuery)
				.setFirstResult(offset)
				.setMaxResults(limit)
				.getResultList();
	}

	@Override
	public Optional<GiftCertificate> findEntityById(long id) {
		return Optional.ofNullable(entityManager.find(GiftCertificate.class, id));
	}

	@Override
	public Optional<GiftCertificate> findEntityByName(String name) {
		Optional<GiftCertificate> giftCertificate = entityManager
				.createQuery(FIND_GIFT_CERTIFICATE_BY_NAME, GiftCertificate.class)
				.setParameter(ColumnName.GIFT_CERTIFICATES_NAME, name)
				.getResultStream()
				.findFirst();
		return giftCertificate;
	}

	@Override
	public GiftCertificate update(GiftCertificate giftCertificate) {
		return entityManager.merge(giftCertificate);
	}

	@Override
	public boolean delete(long id) {
		int row = entityManager.createQuery(DELETE_GIFT_CERTIFICATE)
				.setParameter(ColumnName.GIFT_CERTIFICATES_ID, id)
				.executeUpdate();
		return row > 0;
	}

	@Override
	public long getTotalNumber(Map<String, String> params) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GiftCertificate> criteriaQuery = GiftCertificateQueryBuilder
				.buildQuery(params, criteriaBuilder);
		return entityManager.createQuery(criteriaQuery)
				.getResultStream()
				.count();
	}
}

//private static final String SQL_CREATE_GIFT_CERTIFICATE_TAG = "INSERT INTO gift_certificates_tags "
//		+ "(gift_certificate_id, tag_id) VALUES (?,?)";
//private static final String SQL_DELETE_GIFT_CERTIFICATE_TAG = "DELETE FROM gift_certificates_tags "
//		+ "WHERE gift_certificate_id=?";

