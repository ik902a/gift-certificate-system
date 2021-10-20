package com.epam.esm.dao;

import java.util.List;
import java.util.Map;

import com.epam.esm.entity.GiftCertificateOrder;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;

/**
 * The {@code OrderDao} interface works with orders table in database
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface OrderDao extends BaseDao<Order> {
	
	void createGiftCertificateOrder(GiftCertificateOrder giftCertificateOrder);// TODO 

	List<Order> findOrdersByUser(User user, Map<String, String> params);// TODO new method

	long getTotalNumberByUser(User user, Map<String, String> params);// TODO new method
}
