package com.epam.esm.service;

import java.util.Map;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.PageDto;

/**
 * The {@code OrderService} interface for operations with the order
 * 
 * @author Ihar Klepcha
 */
public interface OrderService {

	/**
	 * Creates order in database
	 * 
	 * @param userId             is user id
	 * @param giftCertificateMap {@link Map} of {@link String} and {@link Integer}
	 *                           map with gift certificate data
	 * @return {@link OrderDto} received from database
	 */
	OrderDto create(long userId, Map<String, Integer> giftCertificateMap);

	/**
	 * Finds order by id
	 * 
	 * @param id is order id
	 * @return {@link OrdreDto} received from database
	 */
	OrderDto findById(long id);

	/**
	 * Finds orders by user
	 * 
	 * @param userId is user id
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return {@link PageDto} of {@link OrderDto} received from database
	 */
	PageDto<OrderDto> findOrdersByUser(long userId, Map<String, String> params);
}
