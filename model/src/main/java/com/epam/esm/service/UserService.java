package com.epam.esm.service;

import java.util.Map;

import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.UserDto;

/**
 * The {@code UserService} interface for operations with user
 * 
 * @author Ihar Klepcha
 */
public interface UserService {
	/**
	 * Creates user in database
	 * 
	 * @param userDto {@link UserDto} user
	 * @return {@link UserDto} received from database
	 */
	UserDto create(UserDto userDto);

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
}
