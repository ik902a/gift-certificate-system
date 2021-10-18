package com.epam.esm.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.entity.GiftCertificateOrder;
import com.epam.esm.entity.Order;

/**
 * The {@code OrderDaoImpl} class works with orders table in database
 * 
 * @author Ihar Klepcha
 * @see OrderDao
 */
@Repository
public class OrderDaoImpl implements OrderDao {
	public static Logger log = LogManager.getLogger();
	private static final String FIND_ALL_ORDERS = "FROM Order";
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Order create(Order order) {
		entityManager.persist(order);
		return order;
	}

	@Override
	public Optional<Order> findEntityById(long id) {
		return Optional.ofNullable(entityManager.find(Order.class, id));
	}

	@Override
	public long getTotalNumber(Map<String, String> params) {
        return entityManager.createQuery(FIND_ALL_ORDERS, Order.class)
        		.getResultStream()
				.count();
	}
	
	@Override
	public void createGiftCertificateOrder(GiftCertificateOrder giftCertificateOrder) {//TODO
		entityManager.persist(giftCertificateOrder);
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
}
