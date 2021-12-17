package com.epam.esm.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.User;

@Disabled
@SpringBootTest(classes = TestConfiguration.class)
@Transactional
public class UserDaoImplTest {
	private UserDao userDao;

	@Autowired
	public UserDaoImplTest(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Test
	public void findTest() {
		List<User> userList = userDao.find(new HashMap<String, String>());
		assertTrue(userList.size() == 3);
	}

	@Test
	public void findEntityByIdTest() {
		User user = new User();
		user.setId(1L);
		user.setLogin("user1");

		Optional<User> actual = userDao.findEntityById(1L);

		assertEquals(user.getId(), actual.get().getId());
	}
}
