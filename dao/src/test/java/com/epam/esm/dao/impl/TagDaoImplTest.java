package com.epam.esm.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.epam.esm.configuration.TestConfiguration;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles(value = "dev")
public class TagDaoImplTest {
	public static Logger log = LogManager.getLogger();
	@Value("classpath:init_db_script.sql")
	private String initTableScript;
	
    @Autowired
    private TagDao tagDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@BeforeEach
	void setUp() {
		new EmbeddedDatabaseBuilder()
		.setType(EmbeddedDatabaseType.H2)
		.addScript(initTableScript)
		.build();
	}
	
    @AfterEach
    void tearDown() {
    	JdbcTestUtils.deleteFromTables(jdbcTemplate, "gift_certificates_tags", "tags", "gift_certificates");
    }
    
    @Test
    public void createPositiveTest() {
    	Tag tagNew = new Tag();
		tagNew.setName("tag4");
		Tag tagCreated = new Tag(4L, "tag4");
        Tag actual = tagDao.create(tagNew);
        assertEquals(tagCreated, actual);
    }
    
    @Test
    void findAllTest() {
        List<Tag> tagList = tagDao.findAll();
        assertTrue(tagList.size() == 3);
    }
    
    @Test
    public void findEntityByIdPositiveTest() {
    	Tag tag = new Tag(1L, "tag1");
        Optional<Tag> actual = tagDao.findEntityById(1);
		assertEquals(Optional.of(tag), actual);
	}
    
    @Test
    public void findEntityByIdNegativeTest() {
        Optional<Tag> actual = tagDao.findEntityById(42);
        assertFalse(actual.isPresent());
    }
    
	@Test
	public void findEntityByNamePositiveTest() {
		Tag tag = new Tag(1L, "tag1");
		Optional<Tag> actual = tagDao.findEntityByName("tag1");
		assertEquals(Optional.of(tag), actual);
	}
	
    @Test
    public void findEntityByNameNegativeTest() {
        Optional<Tag> actual = tagDao.findEntityByName("Sun");
        assertFalse(actual.isPresent());
    }
	@Test
	public void deletePositiveTest() {
		boolean actual = tagDao.delete(2);
		assertTrue(actual);
	}

	@Test
	public void deleteNegativeTest() {
		assertFalse(tagDao.delete(42));
	}
	
	@Test
	public void findEntityByGiftCertificatePositiveTest() {
		Tag tag1 = new Tag(1L, "tag1");
		Tag tag3 = new Tag(3L, "tag3");
		List<Tag> actual = tagDao.findEntityByGiftCertificate(2);
		assertEquals(List.of(tag1, tag3), actual);
	}
}
