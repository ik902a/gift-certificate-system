package com.epam.esm.service;

import java.util.List;
import java.util.Map;

import com.epam.esm.dto.OrderDataDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.PageDto;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;

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

	PageDto<OrderDto> findOrdersByUser(User user, Map<String, String> params);// TODO new method
}
