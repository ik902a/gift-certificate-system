package com.epam.esm.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.util.OrderQueryBuilder;
import com.epam.esm.dao.util.PaginationParamExtractor;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;

/**
 * The {@code OrderDaoImpl} class works with orders table in database
 * 
 * @author Ihar Klepcha
 * @see OrderDao
 */
@Repository
public class OrderDaoImpl implements OrderDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Order create(Order order) {
		entityManager.persist(order);
		order.getGiftCertificateOrderList().forEach(
				giftCertificateOrder -> giftCertificateOrder.getId().setOrderId(order.getId()));
		return order;
	}

	@Override
	public Optional<Order> findEntityById(long id) {
		return Optional.ofNullable(entityManager.find(Order.class, id));
	}

	@Override
	public List<Order> findOrdersByUser(User user, Map<String, String> params) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery = OrderQueryBuilder.buildQueryFindByUser(user, params, criteriaBuilder);
		int offset = PaginationParamExtractor.getOffset(params);
		int limit = PaginationParamExtractor.getLimit(params);
		return entityManager.createQuery(criteriaQuery)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
	}
	
	@Override
	public long getTotalNumberByUser(User user, Map<String, String> params) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery = OrderQueryBuilder.buildQueryFindByUser(user, params, criteriaBuilder);
		return entityManager.createQuery(criteriaQuery)
	        		.getResultStream()
					.count();
	}
	
	@Override
	public Optional<Order> findEntityByName(String name) {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}
	
	@Override
	public List<Order> find(Map<String, String> params) {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public boolean delete(long id) {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}
	
	@Override
	public long getTotalNumber(Map<String, String> params) {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}
}
