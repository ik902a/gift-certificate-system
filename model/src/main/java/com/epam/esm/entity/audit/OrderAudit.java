package com.epam.esm.entity.audit;

import java.time.ZonedDateTime;

import javax.persistence.PrePersist;

import com.epam.esm.entity.Order;

/**
 * Class is listener for order entity.
 *
 * @author Ihar Klepcha
 */
public class OrderAudit {

    @PrePersist
    public void beforeCreateOrder(Order order) {
        order.setDate(ZonedDateTime.now());
    }
}
