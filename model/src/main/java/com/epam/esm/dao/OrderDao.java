package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificateOrder;
import com.epam.esm.entity.Order;

/**
 * The {@code OrderDao} interface works with orders table in database
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface OrderDao extends BaseDao<Order> {
	
	void createGiftCertificateOrder(GiftCertificateOrder giftCertificateOrder);// TODO 
	
}
