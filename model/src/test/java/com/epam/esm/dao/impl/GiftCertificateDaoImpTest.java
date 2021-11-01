package com.epam.esm.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.configuration.TestConfiguration;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

@SpringBootTest(classes = TestConfiguration.class)
@Transactional
public class GiftCertificateDaoImpTest {
	public static Logger log = LogManager.getLogger();

	@Autowired
	private GiftCertificateDao giftCertificateDao;

	@Test
	public void createPositiveTest() {
		GiftCertificate giftCertificateNew = new GiftCertificate();
		giftCertificateNew.setName("Fifth");
		giftCertificateNew.setDescription("Some description 5");
		giftCertificateNew.setPrice(new BigDecimal("50"));
		giftCertificateNew.setDuration(90);
		giftCertificateNew.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateNew.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		GiftCertificate giftCertificateCreated = new GiftCertificate();
		giftCertificateCreated.setId(5L);
		giftCertificateCreated.setName("Fifth");
		giftCertificateCreated.setDescription("Some description 5");
		giftCertificateCreated.setPrice(new BigDecimal("50"));
		giftCertificateCreated.setDuration(90);
		giftCertificateCreated.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateCreated.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		
		GiftCertificate actual = giftCertificateDao.create(giftCertificateNew);
		
		assertEquals(giftCertificateCreated, actual);
	}

	@ParameterizedTest
	@MethodSource("params")
	void findWithParamTest(Map<String, String> params) {
		GiftCertificate giftCertificate = new GiftCertificate();
		giftCertificate.setId(2L);
		giftCertificate.setName("Second");
		giftCertificate.setDescription("Some description 2");
		giftCertificate.setPrice(new BigDecimal("70"));
		giftCertificate.setDuration(42);
		giftCertificate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificate.setTags(List.of(new Tag(1L, "tag1"), new Tag(3L, "tag3")));
		
		List<GiftCertificate> actual = giftCertificateDao.find(params);
		
		assertEquals(List.of(giftCertificate), actual);
	}

	public static Stream<Map<String, String>> params() {
		Map<String, String> firstMap = new HashMap<>();
		firstMap.put("tag", "tag1");
		Map<String, String> secondMap = new HashMap<>();
		secondMap.put("name", "Sec");
		secondMap.put("sort_by", "date");
		Map<String, String> thirdMap = new HashMap<>();
		thirdMap.put("description", "Some description 2");
		thirdMap.put("order_by", "desc");
		return Stream.of(firstMap, secondMap, thirdMap);
	}

	@Test
	void findWithoutParamTest() {
		List<GiftCertificate> giftCertificateList = giftCertificateDao.find(Collections.<String, String>emptyMap());

		assertTrue(giftCertificateList.size() == 4);
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

//	@Test
//	public void updatePositiveTest() {
//		GiftCertificate giftCertificateUpdate = new GiftCertificate();
//		giftCertificateUpdate.setId(3L);
//		giftCertificateUpdate.setName("ThirdUpdate");
//		giftCertificateUpdate.setDescription("Some description 3");
//		giftCertificateUpdate.setPrice(new BigDecimal("10"));
//		giftCertificateUpdate.setDuration(120);
//		giftCertificateUpdate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
//		giftCertificateUpdate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
//		giftCertificateUpdate.setTags(new ArrayList<>());
//		
//		GiftCertificate actual = giftCertificateDao.update(giftCertificateUpdate);
//		
//		assertEquals(giftCertificateUpdate, actual);
//	}

//	@Test
//	public void deletePositiveTest() {
//		boolean actual = giftCertificateDao.delete(4);
//		
//		assertTrue(actual);
//	}
//
//	@Test
//	public void deleteNegativeTest() {
//		boolean actual = giftCertificateDao.delete(42);
//		
//		assertFalse(actual);
//	}

//	@Test
//	public void deleteGiftCertificateTagPositiveTest() {
//		boolean actual = giftCertificateDao.deleteGiftCertificateTag(2);
//		assertTrue(actual);
//	}
//
//	@Test
//	public void deleteGiftCertificateTagNegativeTest() {
//		boolean actual = giftCertificateDao.deleteGiftCertificateTag(42);
//		assertFalse(actual);
//	}
}
