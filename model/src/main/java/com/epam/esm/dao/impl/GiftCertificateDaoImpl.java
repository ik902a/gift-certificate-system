package com.epam.esm.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.util.GiftCertificateQueryBuilder;
import com.epam.esm.util.PaginationParamExtractor;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.ParamName;
import com.epam.esm.entity.Tag;

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
		log.info("I am creating in DAO---------------------------------------------");
		entityManager.persist(giftCertificate);
		return giftCertificate;
	}
	 
		@Override
		public List<GiftCertificate> find(Map<String, String> params) {
			log.info("I am finding in DAO---------------------------------------------");
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<GiftCertificate> criteriaQuery = GiftCertificateQueryBuilder
					.buildQuery(params, criteriaBuilder);
			int offset = PaginationParamExtractor.getOffset(params);
			int limit = PaginationParamExtractor.getLimit(params);
			List<GiftCertificate> giftCertificateList = entityManager.createQuery(criteriaQuery)
					.setFirstResult(offset)
					.setMaxResults(limit)
					.getResultList();	
			return giftCertificateList;
		}
	
	@Override
	public GiftCertificate findEntityById(long id) {
		GiftCertificate giftCertificate = entityManager.find(GiftCertificate.class, id);
		return giftCertificate;
	}
	
	@Override
	public Optional<GiftCertificate> findEntityByName(String name) {
		Optional<GiftCertificate> giftCertificate = entityManager.createQuery(
				FIND_GIFT_CERTIFICATE_BY_NAME, GiftCertificate.class)
				.setParameter(ColumnName.GIFT_CERTIFICATES_NAME, name)
				.getResultStream()
				.findFirst();
		return giftCertificate;
	}
	
	@Override
	public GiftCertificate update(GiftCertificate giftCertificate) {
		giftCertificate = entityManager.merge(giftCertificate);
		return giftCertificate;
	}
	
	@Override
	public boolean delete(long id) {  
		int row = entityManager.createQuery(DELETE_GIFT_CERTIFICATE)
        .setParameter(ColumnName.GIFT_CERTIFICATES_ID, id)
        .executeUpdate();
		return row > 0;
	}

	
	
	

	

//	private static final String SQL_CREATE_GIFT_CERTIFICATE_TAG = "INSERT INTO gift_certificates_tags "
//			+ "(gift_certificate_id, tag_id) VALUES (?,?)";
//	private static final String SQL_DELETE_GIFT_CERTIFICATE_TAG = "DELETE FROM gift_certificates_tags "
//			+ "WHERE gift_certificate_id=?";
//	
//	@Override
//	public void createGiftCertificateTag(GiftCertificate giftCertificate) {
//		giftCertificate.getTags().forEach(
//				tag -> jdbcTemplate.update(SQL_CREATE_GIFT_CERTIFICATE_TAG, 
//						giftCertificate.getId(), 
//						tag.getId()));
//	}
//	
//    @Override
//    public boolean deleteGiftCertificateTag(long id) {
//    	int row = jdbcTemplate.update(SQL_DELETE_GIFT_CERTIFICATE_TAG, id);
//		return row > 0;
//    }
//	
//    private static final class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {
//
//        public GiftCertificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//            GiftCertificate giftCertificate = new GiftCertificate();
//            giftCertificate.setId(resultSet.getLong(GIFT_CERTIFICATES_ID));
//            giftCertificate.setName(resultSet.getString(GIFT_CERTIFICATES_NAME));
//            giftCertificate.setDescription(resultSet.getString(GIFT_CERTIFICATES_DESCRIPTION));
//            giftCertificate.setPrice(resultSet.getBigDecimal(GIFT_CERTIFICATES_PRICE));
//            giftCertificate.setDuration(resultSet.getInt(GIFT_CERTIFICATES_DURATION));
//            giftCertificate.setCreateDate(resultSet.getObject(
//            		GIFT_CERTIFICATES_CREATE_DATE, ZonedDateTime.class));
//            giftCertificate.setLastUpdateDate(resultSet.getObject(
//            		GIFT_CERTIFICATES_LAST_UPDATE_DATE, ZonedDateTime.class));
//            return giftCertificate;
//        }
//    }

	@Override
	public boolean deleteGiftCertificateTag(long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
