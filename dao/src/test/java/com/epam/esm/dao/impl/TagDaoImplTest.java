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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.esm.configuration.ModelConfiguration;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelConfiguration.class)
@ActiveProfiles(value = "dev")
public class TagDaoImplTest {
	public static Logger log = LogManager.getLogger();
	Tag tag1;
	Tag tag3;
	Tag tag4;
	Tag tagAdded;
	
	
    @Autowired
    private TagDao tagDao;
    
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@BeforeEach
	void setUp() {
		tag1 = new Tag(1L, "tag1");
		tag3 = new Tag(3L, "tag3");
		tag4 = new Tag();
		tag4.setName("tag4");
		tagAdded = new Tag(4L, "tag4");
	}
	
    @AfterEach
    void tearDown() {
        tag1 = null;
        tag4 = null;
    	tagAdded = null;
    }
    
    @Test
    public void createPositiveTest() {
        Tag actual = tagDao.create(tag4);
        assertEquals(tagAdded, actual);
    }
    
    @Test
    void findAllTest() {
        List<Tag> tagList = tagDao.findAll();
        assertTrue(tagList.size() == 3);
    }
    
    @Test
    public void findEntityByIdPositiveTest() {
        Optional<Tag> actual = tagDao.findEntityById(1);
		assertEquals(Optional.of(tag1), actual);
	}
    
    @Test
    public void findEntityByIdNegativeTest() {
        Optional<Tag> actual = tagDao.findEntityById(42);
        assertFalse(actual.isPresent());
    }
    
	@Test
	public void findEntityByNamePositiveTest() {
		Optional<Tag> actual = tagDao.findEntityByName("tag1");
		assertEquals(Optional.of(tag1), actual);
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
		List<Tag> actual = tagDao.findEntityByGiftCertificate(2);
		assertEquals(List.of(tag1, tag3), actual);
	}
}
