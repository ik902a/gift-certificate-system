package com.epam.esm.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

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
	private GiftCertificateDao giftCertificateDao;

	@Autowired
	public GiftCertificateDaoImpTest(GiftCertificateDao giftCertificateDao) {
		super();
		this.giftCertificateDao = giftCertificateDao;
	}

	@Test
	public void createPositiveTest() {
		GiftCertificate giftCertificateNew = new GiftCertificate();
		giftCertificateNew.setName("Fifth");
		giftCertificateNew.setDescription("Some description 5");
		giftCertificateNew.setPrice(new BigDecimal(50.00));
		giftCertificateNew.setDuration(90);
		giftCertificateNew.setCreateDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		giftCertificateNew.setLastUpdateDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		GiftCertificate giftCertificateCreated = new GiftCertificate();
		giftCertificateCreated.setId(5L);
		giftCertificateCreated.setName("Fifth");
		giftCertificateCreated.setDescription("Some description 5");
		giftCertificateCreated.setPrice(new BigDecimal("50"));
		giftCertificateCreated.setDuration(90);
		giftCertificateCreated.setCreateDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		giftCertificateCreated.setLastUpdateDate(LocalDateTime.parse("2021-08-12T08:12:15"));

		GiftCertificate actual = giftCertificateDao.create(giftCertificateNew);
		
		assertEquals(giftCertificateCreated.getId(), actual.getId());
	}

	@ParameterizedTest
	@MethodSource("params")
	void findTest(Map<String, String> params) {
		GiftCertificate giftCertificate = new GiftCertificate();
		giftCertificate.setId(2L);
		giftCertificate.setName("Second");
		giftCertificate.setDescription("Some description 2");
		giftCertificate.setPrice(new BigDecimal("70"));
		giftCertificate.setDuration(42);
		giftCertificate.setCreateDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		giftCertificate.setLastUpdateDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		giftCertificate.setTags(List.of(new Tag(1L, "tag1"), new Tag(3L, "tag3")));

		List<GiftCertificate> actual = giftCertificateDao.find(params);

		assertTrue(actual.size() == 1);
	}

	public static Stream<Map<String, String>> params() {
		Map<String, String> firstMap = new HashMap<>();
		firstMap.put("tag", "tag1");
		Map<String, String> secondMap = new HashMap<>();
		secondMap.put("name", "Sec");
		Map<String, String> thirdMap = new HashMap<>();
		thirdMap.put("description", "Some description 2");
		thirdMap.put("order_by", "desc");
		return Stream.of(firstMap, secondMap, thirdMap);
	}

	@Test
	public void findEntityByIdPositiveTest() {
		GiftCertificate giftCertificate = new GiftCertificate();
		giftCertificate.setId(1L);
		giftCertificate.setName("First");
		giftCertificate.setDescription("Some description 1");
		giftCertificate.setPrice(new BigDecimal("50"));
		giftCertificate.setDuration(90);
		giftCertificate.setCreateDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		giftCertificate.setLastUpdateDate(LocalDateTime.parse("2021-08-12T08:12:15"));

		Optional<GiftCertificate> actual = giftCertificateDao.findEntityById(1);

		assertEquals(giftCertificate.getId(), actual.get().getId());
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
		giftCertificate.setCreateDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		giftCertificate.setLastUpdateDate(LocalDateTime.parse("2021-08-12T08:12:15"));

		Optional<GiftCertificate> actual = giftCertificateDao.findEntityByName("First");

		assertEquals(giftCertificate.getName(), actual.get().getName());
	}

	@Test
	public void findEntityByNameNegativeTest() {
		Optional<GiftCertificate> actual = giftCertificateDao.findEntityByName("Sun");
		assertFalse(actual.isPresent());
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
}
