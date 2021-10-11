package com.epam.esm.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.User;
import com.epam.esm.util.PaginationParamExtractor;

/**
 * The {@code UserDaoImpl} class works with users table in database
 * 
 * @author Ihar Klepcha
 * @see UserDao
 */
@Repository
public class UserDaoImpl implements UserDao {
	private static final String FIND_ALL_USERS = "FROM User";
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public User create(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public List<User> find(Map<String, String> params) {
		int offset = PaginationParamExtractor.getOffset(params);
		int limit = PaginationParamExtractor.getLimit(params);
        List<User> userList = entityManager.createQuery(FIND_ALL_USERS, User.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        return userList;
	}

	@Override
	public User findEntityById(long id) {
		User user = entityManager.find(User.class, id);
		return user;
	}

	@Override
	public Optional<User> findEntityByName(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getTotalNumber(Map<String, String> params) {
		// TODO Auto-generated method stub
		return 0;
	}

}
