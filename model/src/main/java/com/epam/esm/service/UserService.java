package com.epam.esm.service;

import java.util.List;
import java.util.Map;

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
	 * @return {@link List} of {@link UserDto} received from database
	 */
	List<UserDto> find(Map<String, String> params);

	/**
	 * Finds user by id
	 * 
	 * @param id is user id
	 * @return {@link UserDto} received from database
	 */
	UserDto findById(long id);
	
	UserDto create(UserDto userDto);
}