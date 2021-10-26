package com.epam.esm.service;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.UserDto;

/**
 * The {@code UserService} interface for operations with user
 * 
 * @author Ihar Klepcha
 */
public interface UserService {
	/**
	 * Finds user by parameters
	 * 
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 * @return {@link PageDto} of {@link UserDto} received from database
	 */
	PageDto<UserDto> find(Map<String, String> params);

	/**
	 * Finds user by id
	 * 
	 * @param id is user id
	 * @return {@link UserDto} received from database
	 */
	UserDto findById(long id);
	
	UserDto create(UserDto userDto);

	PageDto<OrderDto> findOrdersByUser(long id, Map<String, String> params);

	OrderDto createOrder(long userId, Map<Long, Integer> orderData);

	OrderDto findOrderById(long id);
}
