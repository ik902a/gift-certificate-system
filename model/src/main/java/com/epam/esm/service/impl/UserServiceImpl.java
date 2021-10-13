package com.epam.esm.service.impl;

import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.OrderForUserDto;
import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ResourceNotExistException;
import com.epam.esm.service.UserService;

/**
 * The {@code UserServiceImpl} class is responsible for operations with user
 * 
 * @author Ihar Klepcha
 * @see UserService
 */
@Service
public class UserServiceImpl implements UserService {
	public static Logger log = LogManager.getLogger();
	@Autowired
	private UserDao userDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public PageDto<UserDto> find(Map<String, String> params) {
		log.info("FIND User BY PARAMS Service {}", params);
		List<User> userList = userDao.find(params);
		List<UserDto> userDtoList = userList.stream()
                .map(user -> convertToDto(user))
                .collect(Collectors.toList());
		log.info("i'm here Service {}-------------", params);
	    long totalPositions = userDao.getTotalNumber(params);
	    log.info("i'm here Service {}-------------", params);
		return new PageDto<>(userDtoList, totalPositions);
	}

	private UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		List<OrderForUserDto> orderForUserDtoList = user.getOrderList().stream()
				.map(order -> modelMapper.map(order, OrderForUserDto.class))
				.collect(Collectors.toList());
				userDto.setOrders(orderForUserDtoList);
		return userDto;
	}

	@Override
	@Transactional
	public UserDto findById(long id) {
		log.info("FIND User BY ID Service id={}", id);
		Optional<User> userOptional = userDao.findEntityById(id);
		User user = userOptional.orElseThrow(
			() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
					, id
					, USER_INCORRECT.getErrorCode()));
		return convertToDto(user);
	}

	@Override
	@Transactional
	public UserDto create(UserDto userDto) {
		log.info("CREATE user Service {}", userDto);
		User user = modelMapper.map(userDto, User.class);
		user = userDao.create(user);
		return modelMapper.map(user, UserDto.class);
	}
}
