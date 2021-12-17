package com.epam.esm.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.configuration.TestConfiguration;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;

@Disabled
@SpringBootTest(classes = TestConfiguration.class)
@Transactional
public class TagDaoImplTest {
	private TagDao tagDao;

	@Autowired
	public TagDaoImplTest(TagDao tagDao) {
		super();
		this.tagDao = tagDao;
	}

	@Test
	public void createPositiveTest() {
		Tag tagNew = new Tag();
		tagNew.setName("tag4");
		Tag tagCreated = new Tag(4L, "tag4");

		Tag actual = tagDao.create(tagNew);
		
		assertEquals(tagCreated.getId(), actual.getId());
	}

	@Test
	void findTest() {
		List<Tag> tagList = tagDao.find(new HashMap<String, String>());
		assertTrue(tagList.size() == 3);
	}

	@Test
	public void findEntityByIdPositiveTest() {
		Tag tag = new Tag(1L, "tag1");

		Optional<Tag> actual = tagDao.findEntityById(1);
		
		assertEquals(tag.getId(), actual.get().getId());
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
		
		assertEquals(tag.getName(), actual.get().getName());
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
	public void findMostPopularTagOfUserWithHighestCostOfAllOrdersTest() {
		Tag tag = new Tag(1L, "tag1");

		Optional<Tag> actual = tagDao.findMostPopularTagOfUserWithHighestCostOfAllOrders();

		assertEquals(tag.getName(), actual.get().getName());
	}
}
