package com.epam.esm.dao.impl;

import static com.epam.esm.dao.ColumnName.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;

/**
 * The {@code TagDaoImpl} class works with tags table in database
 * 
 * @author Ihar Klepcha
 * @see TagDao
 */
@Repository
public class TagDaoImpl implements TagDao {
	private static final String SQL_CREATE_TAG = "INSERT INTO tags (name) VALUES (?)";
	private static final String SQL_FIND_ALL_TAGS = "SELECT id, name FROM tags";
	private static final String SQL_FIND_TAG_BY_ID = "SELECT id, name FROM tags WHERE id=?";
	private static final String SQL_FIND_TAG_BY_NAME = "SELECT id, name FROM tags WHERE name=?";
	private static final String SQL_DELETE_TAG = "DELETE FROM tags WHERE id=?";
	private static final String SQL_FIND_TAG_BY_GIFT_CERTIFICATE = "SELECT id, name FROM tags WHERE id IN "
			+ "(SELECT tag_id FROM gift_certificates_tags gct "
			+ "JOIN gift_certificates gc ON gct.gift_certificate_id = gc.id WHERE gc.id=?)";	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Tag create(Tag tag) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(SQL_CREATE_TAG, 
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, tag.getName());
			return statement;
		}, keyHolder);
		if (keyHolder.getKey() != null) {
			tag.setId(keyHolder.getKey().longValue());
		}
		return tag;
	}

	@Override
	public List<Tag> findAll() {
		List<Tag> tagList = jdbcTemplate.query(SQL_FIND_ALL_TAGS, new TagRowMapper());
		return tagList;
	}

	@Override
	public Optional<Tag> findEntityById(long id) {
		Optional<Tag> tag = jdbcTemplate.queryForStream(SQL_FIND_TAG_BY_ID, new TagRowMapper(), id)
				.findFirst();
		return tag;
	}

	@Override
	public Optional<Tag> findEntityByName(String name) {
		Optional<Tag> tag = jdbcTemplate.queryForStream(SQL_FIND_TAG_BY_NAME, new TagRowMapper(), name)
				.findFirst();
		return tag;
	}

	@Override
	public boolean delete(long id) {
		int row = jdbcTemplate.update(SQL_DELETE_TAG, id);
		return row > 0;
	}

	@Override
	public List<Tag> findEntityByGiftCertificate(long giftCertificateId) {
		List<Tag> tagList = jdbcTemplate.query(
				SQL_FIND_TAG_BY_GIFT_CERTIFICATE, new TagRowMapper(), giftCertificateId);
		return tagList;
	}
	
	private static final class TagRowMapper implements RowMapper<Tag> {

		public Tag mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Tag tag = new Tag();
			tag.setId(resultSet.getLong(TAGS_ID));
			tag.setName(resultSet.getString(TAGS_NAME));
			return tag;
		}
	}
}
