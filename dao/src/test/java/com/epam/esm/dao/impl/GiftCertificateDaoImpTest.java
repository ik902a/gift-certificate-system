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

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
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

import com.epam.esm.configuration.ModelConfiguration;
import com.epam.esm.configuration.TestConfiguration;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles(value = "dev")
public class GiftCertificateDaoImpTest {
	public static Logger log = LogManager.getLogger();
	@Value("classpath:gift_certificates_db_script.sql")
	private String createTableScript;
	@Value("classpath:init_db_script.sql")
	private String initTableScript;
	
    @Autowired
    private GiftCertificateDao giftCertificateDao;
    
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource embeddedDataSource;
	
	@BeforeEach
	void setUp() {
//		embeddedDataSource.createConnectionBuilder(). = new EmbeddedDatabaseBuilder()
//		.setType(EmbeddedDatabaseType.H2)
//
//		.addScript(initTableScript).build();
}
//		
////		giftCertificate = new GiftCertificate();
////		giftCertificate.setId(1L);
////		giftCertificate.setName("First");
////		giftCertificate.setDescription("Some description 1");
////		giftCertificate.setPrice(new BigDecimal("50"));
////		giftCertificate.setDuration(90);
////		giftCertificate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
////		giftCertificate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
////		giftCertificate5 = new GiftCertificate();
////		giftCertificate5.setName("Fifth");
////		giftCertificate5.setDescription("Some description 5");
////		giftCertificate5.setPrice(new BigDecimal("50"));
////		giftCertificate5.setDuration(90);
////		giftCertificate5.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
////		giftCertificate5.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
////		giftCertificateAdded = new GiftCertificate();
////		giftCertificateAdded.setId(5L);
////		giftCertificateAdded.setName("Fifth");
////		giftCertificateAdded.setDescription("Some description 5");
////		giftCertificateAdded.setPrice(new BigDecimal("50"));
////		giftCertificateAdded.setDuration(90);
////		giftCertificateAdded.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
////		giftCertificateAdded.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
////		giftCertificateUpdate = new GiftCertificate();
////		giftCertificateUpdate.setId(3L);
////		giftCertificateUpdate.setName("ThirdUpdate");
////		giftCertificateUpdate.setDescription("Some description 3");
////		giftCertificateUpdate.setPrice(new BigDecimal("10"));
////		giftCertificateUpdate.setDuration(120);
////		giftCertificateUpdate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
////		giftCertificateUpdate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
////		giftCertificateUpdate.setTags(new ArrayList<>());
////		giftCertificateSort = new GiftCertificate();
////		giftCertificateSort.setId(2L);
////		giftCertificateSort.setName("Second");
////		giftCertificateSort.setDescription("Some description 2");
////		giftCertificateSort.setPrice(new BigDecimal("70"));
////		giftCertificateSort.setDuration(42);
////		giftCertificateSort.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
////		giftCertificateSort.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
////		giftCertificateSort.setTags(List.of(new Tag(1L, "tag1"), new Tag(3L, "tag3")));
////	}
 
    
    @AfterEach
    void tearDown() {
    	JdbcTestUtils.deleteFromTables(jdbcTemplate, "gift_certificates_tags", "tags", "gift_certificates");
    }
//    @After
//    public void tearDown() {
//        JdbcTestUtils.dropTables(jdbcTemplate, "orders", "customers");
//    }
    
    @Test
    public void createPositiveTest() {
    	GiftCertificate giftCertificate5 = new GiftCertificate();
		giftCertificate5.setName("Fifth");
		giftCertificate5.setDescription("Some description 5");
		giftCertificate5.setPrice(new BigDecimal("50"));
		giftCertificate5.setDuration(90);
		giftCertificate5.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificate5.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		GiftCertificate giftCertificateAdded = new GiftCertificate();
		giftCertificateAdded.setId(5L);
		giftCertificateAdded.setName("Fifth");
		giftCertificateAdded.setDescription("Some description 5");
		giftCertificateAdded.setPrice(new BigDecimal("50"));
		giftCertificateAdded.setDuration(90);
		giftCertificateAdded.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateAdded.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		
        GiftCertificate actual = giftCertificateDao.create(giftCertificate5);
        assertEquals(giftCertificateAdded, actual);
    }
    
    
    
    
    
    
    @Test (dataProvider = "invalidEmpty")
    void findTest() {
        List<GiftCertificate> giftCertificateList = giftCertificateDao.find();
        log.info("giftCertificateList.size()={}" , giftCertificateList.size());
        log.info(giftCertificateList.toString());
        assertTrue(giftCertificateList.size() == 4);
    }
    
    
	@Test(dataProvider = "invalidEmpty")
	public void isEmptyNegativeTest(String inputtedData) {
		boolean value = OrderDataValidator.isEmpty(inputtedData);
		assertFalse(value);
	}
	
	@DataProvider(name = "validMessage")
	public static Object[][] createValidMessage() {
		return new Object[][] { { "Help me!" }, { "Please" }, { "Поможите" } };
	}
    
    
    
    
    
    @Test
    public void findEntityByIdPositiveTest() {
    	GiftCertificate giftCertificate = new GiftCertificate();
		giftCertificate.setId(1L);
		giftCertificate.setName("First");
		giftCertificate.setDescription("Some description 1");
		giftCertificate.setPrice(new BigDecimal("50"));
		giftCertificate.setDuration(90);
		giftCertificate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
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
		GiftCertificate giftCertificate = new GiftCertificate();
		giftCertificate.setId(1L);
		giftCertificate.setName("First");
		giftCertificate.setDescription("Some description 1");
		giftCertificate.setPrice(new BigDecimal("50"));
		giftCertificate.setDuration(90);
		giftCertificate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		Optional<GiftCertificate> actual = giftCertificateDao.findEntityByName("First");
		assertEquals(Optional.of(giftCertificate), actual);
	}
	
    @Test
    public void findEntityByNameNegativeTest() {
        Optional<GiftCertificate> actual = giftCertificateDao.findEntityByName("Sun");
        assertFalse(actual.isPresent());
    }

	@Test
	public void updatePositiveTest() {
		GiftCertificate giftCertificateUpdate = new GiftCertificate();
		giftCertificateUpdate.setId(3L);
		giftCertificateUpdate.setName("ThirdUpdate");
		giftCertificateUpdate.setDescription("Some description 3");
		giftCertificateUpdate.setPrice(new BigDecimal("10"));
		giftCertificateUpdate.setDuration(120);
		giftCertificateUpdate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateUpdate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateUpdate.setTags(new ArrayList<>());
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
		boolean actual = giftCertificateDao.delete(42);
		assertFalse(actual);
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

//    @Test
//    public void findEntityByTagNamePositiveTest() {
//        List<GiftCertificate> actual = giftCertificateDao.findEntityByTagName("tag1", "asc");
//        assertEquals(List.of(giftCertificateSort), actual);
//    }
// 
//    @Test
//    public void findEntityByPartNamePositiveTest() {
//        List<GiftCertificate> actual = giftCertificateDao.findEntityByPartName("Sec", "asc");
//        assertEquals(List.of(giftCertificateSort), actual);
//    }
//    
//    @Test
//    public void findEntityByPartDescriptionPositiveTest() {
//        List<GiftCertificate> actual = 
//        		giftCertificateDao.findEntityByPartDescription("Some description 2", "asc");
//        assertEquals(List.of(giftCertificateSort), actual);
//    }
}
