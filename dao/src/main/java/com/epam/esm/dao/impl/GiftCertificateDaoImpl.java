package com.epam.esm.dao.impl;

import static com.epam.esm.dao.ColumnName.*;
import static com.epam.esm.entity.OrderType.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.OrderType;
import com.epam.esm.entity.GiftCertificate;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
	public static Logger log = LogManager.getLogger();
	private static final String SQL_CREATE_GIFT_CERTIFICATE = "INSERT INTO gift_certificates "
			+ "(name, description, price, duration, create_date, last_update_date) VALUES (?,?,?,?,?,?)";
	private static final String SQL_CREATE_GIFT_CERTIFICATE_TAG = "INSERT INTO gift_certificates_tags "
			+ "(gift_certificate_id, tag_id) VALUES (?,?)";
	private static final String SQL_FIND_ALL_GIFT_CERTIFICATES = "SELECT id, name, description, price, "
			+ "duration, create_date, last_update_date FROM gift_certificates";
    private static final String SQL_FIND_GIFT_CERTIFICATE_BY_ID = "SELECT id, name, description, price, "
			+ "duration, create_date, last_update_date FROM gift_certificates WHERE id=?";
	private static final String SQL_FIND_GIFT_CERTIFICATE_BY_NAME = "SELECT id, name, description, "
			+ "price, duration, create_date, last_update_date FROM gift_certificates WHERE name=?";
	private static final String SQL_UPDATE_GIFT_CERTIFICATE = "UPDATE gift_certificates "
			+ "SET name=?, description=?, price=?, duration=?, last_update_date=? WHERE id=?";
	private static final String SQL_DELETE_GIFT_CERTIFICATE = "DELETE FROM gift_certificates WHERE id=?";
	private static final String SQL_DELETE_GIFT_CERTIFICATE_TAG = "DELETE FROM gift_certificates_tags "
			+ "WHERE gift_certificate_id=?";
	private static final String SQL_FIND_GIFT_CERTIFICATE_BY_TAG = "SELECT id, name, description, "
			+ "price, duration, create_date, last_update_date FROM gift_certificates WHERE id IN "
			+ "(SELECT gift_certificate_id FROM gift_certificates_tags "
			+ "JOIN tags ON gift_certificates_tags.tag_id = tags.id WHERE tags.name=?) ORDER BY name ";
	private static final String SQL_SELECT_GIFT_CERTIFICATE_BY_NAME_AND_ORDER = "SELECT id, name, "
			+ "description, price, duration, create_date, last_update_date FROM gift_certificates "
			+ "WHERE name LIKE CONCAT('%',?,'%') ORDER BY name ";
	private static final String SQL_SELECT_GIFT_CERTIFICATE_BY_DESCRIPTION_AND_ORDER ="SELECT id, name, "
			+ "description, price, duration, create_date, last_update_date FROM gift_certificates "
			+ "WHERE description LIKE CONCAT('%',?,'%') ORDER BY name ";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TagDaoImpl tagDao;

	@Override
	public GiftCertificate create(GiftCertificate giftCertificate) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(SQL_CREATE_GIFT_CERTIFICATE,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, giftCertificate.getName());
			statement.setString(2, giftCertificate.getDescription());
			statement.setBigDecimal(3, giftCertificate.getPrice());
			statement.setInt(4, giftCertificate.getDuration());
			statement.setObject(5, giftCertificate.getCreateDate());
			statement.setObject(6, giftCertificate.getLastUpdateDate());
			return statement;
		}, keyHolder);
		if (keyHolder.getKey() != null) {
			giftCertificate.setId(keyHolder.getKey().longValue());
		}
		return giftCertificate;
	}
	
	@Override
	public void createGiftCertificateTag(GiftCertificate giftCertificate) {
		giftCertificate.getTags().forEach(
				tag -> jdbcTemplate.update(SQL_CREATE_GIFT_CERTIFICATE_TAG, 
						giftCertificate.getId(), tag.getId()));
	}
	
	@Override
	public List<GiftCertificate> findAll() {
		log.info("DAO find all------------------------");
		List<GiftCertificate> giftCertificateList = jdbcTemplate.query(
				SQL_FIND_ALL_GIFT_CERTIFICATES, new GiftCertificateRowMapper());
		return giftCertificateList;
	}
	
	@Override
	public Optional<GiftCertificate> findEntityById(long id) {
		 Optional<GiftCertificate> giftCertificate = jdbcTemplate.queryForStream(
				 SQL_FIND_GIFT_CERTIFICATE_BY_ID, new GiftCertificateRowMapper(), id)
				 .findFirst();
		 log.debug("DAO Entitty {}", giftCertificate);
		return giftCertificate;
	}
	
	@Override
	public Optional<GiftCertificate> findEntityByName(String name) {
		Optional<GiftCertificate> giftCertificate = jdbcTemplate.queryForStream(
				 SQL_FIND_GIFT_CERTIFICATE_BY_NAME, new GiftCertificateRowMapper(), name)
				.findFirst();
		return giftCertificate;
	}
	
	@Override
	public GiftCertificate update(GiftCertificate giftCertificate) {
		jdbcTemplate.update(SQL_UPDATE_GIFT_CERTIFICATE, 
				giftCertificate.getName(), 
				giftCertificate.getDescription(), 
				giftCertificate.getPrice(), 
				giftCertificate.getDuration(), 
				giftCertificate.getLastUpdateDate(),
				giftCertificate.getId());
		return giftCertificate;
	}
	
	@Override
	public boolean delete(long id) {
		int row = jdbcTemplate.update(SQL_DELETE_GIFT_CERTIFICATE, id);
		return row > 0;
	}
	
    @Override
    public boolean deleteGiftCertificateTag(long id) {
    	int row = jdbcTemplate.update(SQL_DELETE_GIFT_CERTIFICATE_TAG, id);
		return row > 0;
    }
    
	@Override
	public List<GiftCertificate> findEntityByTagName(String tagName, String sortBy) {
		List<GiftCertificate> giftCertificateList = jdbcTemplate.query(
				 SQL_FIND_GIFT_CERTIFICATE_BY_TAG, new GiftCertificateRowMapper(), tagName);
		return addTags(giftCertificateList);
	}
    
    @Override
    public List<GiftCertificate> findEntityByPartName(String name, String orderBy) {
        List<GiftCertificate> giftCertificateList = jdbcTemplate.query(
                addOrder(SQL_SELECT_GIFT_CERTIFICATE_BY_NAME_AND_ORDER, orderBy), 
                new GiftCertificateRowMapper(), name);     
        return addTags(giftCertificateList);
    }
 
    @Override
    public List<GiftCertificate> findEntityByPartDescription(String description, String orderBy) {
        List<GiftCertificate> giftCertificateList = jdbcTemplate.query(
                addOrder(SQL_SELECT_GIFT_CERTIFICATE_BY_DESCRIPTION_AND_ORDER, orderBy), 
                new GiftCertificateRowMapper(), description);
        return addTags(giftCertificateList);
    }
    
	private List<GiftCertificate> addTags(List<GiftCertificate> giftCertificateList) {
		giftCertificateList.forEach(gc -> gc.setTags(tagDao.findEntityByGiftCertificate(gc.getId())));
		return giftCertificateList;
	}
    
    private String addOrder (String sqlQuery, String orderBy) {
           switch (OrderType.valueOf(orderBy.toUpperCase())) {
               case ASC:
                   sqlQuery += ASC.toString();
                   break;
               case DESC:
            	   sqlQuery += DESC.toString();
               default:
                   throw new IllegalArgumentException("Invalid order: {}" + orderBy);//TODO
           }
           return sqlQuery;
    }
	
    private static final class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {

        public GiftCertificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            GiftCertificate giftCertificate = new GiftCertificate();
            giftCertificate.setId(resultSet.getLong(GIFT_CERTIFICATES_ID));
            giftCertificate.setName(resultSet.getString(GIFT_CERTIFICATES_NAME));
            giftCertificate.setDescription(resultSet.getString(GIFT_CERTIFICATES_DESCRIPTION));
            giftCertificate.setPrice(resultSet.getBigDecimal(GIFT_CERTIFICATES_PRICE));
            giftCertificate.setDuration(resultSet.getInt(GIFT_CERTIFICATES_DURATION));
            giftCertificate.setCreateDate(resultSet.getObject(
            		GIFT_CERTIFICATES_CREATE_DATE, ZonedDateTime.class));
            giftCertificate.setLastUpdateDate(resultSet.getObject(
            		GIFT_CERTIFICATES_LAST_UPDATE_DATE, ZonedDateTime.class));
            return giftCertificate;
        }
    }
}
