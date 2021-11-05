package com.epam.esm.entity.audit;

import java.time.ZonedDateTime;

import javax.persistence.PrePersist;

import com.epam.esm.entity.Order;

/**
 * The {@code OrderAudit} class is listener for order entity
 *
 * @author Ihar Klepcha
 */
public class OrderAudit {

	/**
	 * Adds date before creating order
	 * 
	 * @param order {@link Order} order
	 */
    @PrePersist
    public void beforeCreateOrder(Order order) {
        order.setDate(ZonedDateTime.now());
    }
}
