package com.epam.esm.service;

import com.epam.esm.dto.OrderDataDto;
import com.epam.esm.dto.OrderDto;

/**
 * The {@code OrderService} interface for operations with the order
 * 
 * @author Ihar Klepcha
 */
public interface OrderService {
	
	/**
	 * Creates order in database
	 * 
	 * @param order {@link OrderDto} order DTO
	 * @return {@link OrderDto} received from database
	 */
	OrderDto create(OrderDataDto orderDataDto);
	
	/**
	 * Finds order by id
	 * 
	 * @param id is order id
	 * @return {@link OrdreDto} received from database
	 */
	OrderDto findById(long id);
}
