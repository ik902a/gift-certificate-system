package com.epam.esm.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.util.PaginationParamExtractor;
import com.epam.esm.dao.util.UserQueryBuilder;
import com.epam.esm.entity.User;

/**
 * The {@code UserDaoImpl} class works with users table in database
 * 
 * @author Ihar Klepcha
 * @see UserDao
 */
@Repository
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> find(Map<String, String> params) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = UserQueryBuilder.buildQueryFindByParams(params, criteriaBuilder);
		int offset = PaginationParamExtractor.getOffset(params);
		int limit = PaginationParamExtractor.getLimit(params);
		return entityManager.createQuery(criteriaQuery)
							.setFirstResult(offset)
							.setMaxResults(limit)
							.getResultList();
	}

	@Override
	public Optional<User> findEntityById(long id) {
		return Optional.ofNullable(entityManager.find(User.class, id));
	}

	@Override
	public long getTotalNumber(Map<String, String> params) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = UserQueryBuilder.buildQueryFindByParams(params, criteriaBuilder);
		return entityManager.createQuery(criteriaQuery)
							.getResultStream()
							.count();
	}

	@Override
	public User create(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public Optional<User> findEntityByName(String login) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = UserQueryBuilder.buildQueryFindEntityByName(login, criteriaBuilder);
		return entityManager.createQuery(criteriaQuery)
							.getResultStream()
							.findFirst();
	}

	@Override
	public boolean delete(long id) {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}
}
