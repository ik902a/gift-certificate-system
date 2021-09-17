package com.epam.esm.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.epam.esm.configuration.ModelConfiguration;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelConfiguration.class)
@ActiveProfiles(value = "dev")
public class GiftCertificateDaoImpTest {
	public static Logger log = LogManager.getLogger();
	GiftCertificate giftCertificate;
	GiftCertificate giftCertificate5;
	GiftCertificate giftCertificateAdded;
	GiftCertificate giftCertificateUpdate;
	GiftCertificate giftCertificateSort;
	
    @Autowired
    private GiftCertificateDao giftCertificateDao;
    
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@BeforeEach
	void setUp() {
		giftCertificate = new GiftCertificate();
		giftCertificate.setId(1L);
		giftCertificate.setName("First");
		giftCertificate.setDescription("Some description 1");
		giftCertificate.setPrice(new BigDecimal("50"));
		giftCertificate.setDuration(90);
		giftCertificate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificate5 = new GiftCertificate();
		giftCertificate5.setName("Fifth");
		giftCertificate5.setDescription("Some description 5");
		giftCertificate5.setPrice(new BigDecimal("50"));
		giftCertificate5.setDuration(90);
		giftCertificate5.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificate5.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateAdded = new GiftCertificate();
		giftCertificateAdded.setId(5L);
		giftCertificateAdded.setName("Fifth");
		giftCertificateAdded.setDescription("Some description 5");
		giftCertificateAdded.setPrice(new BigDecimal("50"));
		giftCertificateAdded.setDuration(90);
		giftCertificateAdded.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateAdded.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateUpdate = new GiftCertificate();
		giftCertificateUpdate.setId(3L);
		giftCertificateUpdate.setName("ThirdUpdate");
		giftCertificateUpdate.setDescription("Some description 3");
		giftCertificateUpdate.setPrice(new BigDecimal("10"));
		giftCertificateUpdate.setDuration(120);
		giftCertificateUpdate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateUpdate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateUpdate.setTags(new ArrayList<>());
		giftCertificateSort = new GiftCertificate();
		giftCertificateSort.setId(2L);
		giftCertificateSort.setName("Second");
		giftCertificateSort.setDescription("Some description 2");
		giftCertificateSort.setPrice(new BigDecimal("70"));
		giftCertificateSort.setDuration(42);
		giftCertificateSort.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateSort.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateSort.setTags(List.of(new Tag(1L, "tag1"), new Tag(3L, "tag3")));
	}
 
    
    @AfterEach
    void tearDown() {
        giftCertificate = null;
    	giftCertificateUpdate = null;
    	giftCertificateSort = null;
    }
    
//	@AfterAll
//	void baseTearDown() {
//		JdbcTestUtils.deleteFromTables(jdbcTemplate, "gift_certificates_tags", "tags", "gift_certificates");
//	}
    
    @Test
    public void createPositiveTest() {
        GiftCertificate actual = giftCertificateDao.create(giftCertificate5);
        assertEquals(giftCertificateAdded, actual);
    }
    
    @Test
    void findAllTest() {
        List<GiftCertificate> giftCertificateList = giftCertificateDao.findAll();
        log.info("giftCertificateList.size()={}" , giftCertificateList.size());
        log.info(giftCertificateList.toString());
        assertTrue(giftCertificateList.size() == 4);
    }
    
    @Test
    public void findEntityByIdPositiveTest() {
        Optional<GiftCertificate> actual = giftCertificateDao.findEntityById(1);
        log.info(actual.toString());
		assertEquals(Optional.of(giftCertificate), actual);
	}
    
    @Test
    public void findEntityByIdNegativeTest() {
        Optional<GiftCertificate> actual = giftCertificateDao.findEntityById(15);
        assertFalse(actual.isPresent());
    }

	@Test
	public void findEntityByNamePositiveTest() {
		Optional<GiftCertificate> actual = giftCertificateDao.findEntityByName("First");
		log.info(actual.toString());
		assertEquals(Optional.of(giftCertificate), actual);
	}
	
    @Test
    public void findEntityByNameNegativeTest() {
        Optional<GiftCertificate> actual = giftCertificateDao.findEntityByName("Sun");
        assertFalse(actual.isPresent());
    }

	@Test
	public void updatePositiveTest() {
		GiftCertificate actual = giftCertificateDao.update(giftCertificateUpdate);
		assertEquals(giftCertificateUpdate, actual);
	}
	
	@Test
	public void deletePositiveTest() {
		boolean actual = giftCertificateDao.delete(4);
		assertTrue(actual);
	}

	@Test
	public void deleteNegativeTest() {
		assertFalse(giftCertificateDao.delete(42));
	}

	@Test
	public void deleteGiftCertificateTagPositiveTest() {
		boolean actual = giftCertificateDao.deleteGiftCertificateTag(2);
        assertTrue(actual);
    }
	
	@Test
	public void deleteGiftCertificateTagNegativeTest() {
		boolean actual = giftCertificateDao.deleteGiftCertificateTag(42);
		assertFalse(actual);
    }

    @Test
    public void findEntityByTagNamePositiveTest() {
        List<GiftCertificate> actual = giftCertificateDao.findEntityByTagName("tag1", "asc");
        assertEquals(List.of(giftCertificateSort), actual);
    }
 
    @Test
    public void findEntityByPartNamePositiveTest() {
        List<GiftCertificate> actual = giftCertificateDao.findEntityByPartName("Sec", "asc");
        assertEquals(List.of(giftCertificateSort), actual);
    }
    
    @Test
    public void findEntityByPartDescriptionPositiveTest() {
        List<GiftCertificate> actual = 
        		giftCertificateDao.findEntityByPartDescription("Some description 2", "asc");
        assertEquals(List.of(giftCertificateSort), actual);
    }
}
