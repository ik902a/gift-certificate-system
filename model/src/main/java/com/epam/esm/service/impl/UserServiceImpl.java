package com.epam.esm.service.impl;

import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;
import static com.epam.esm.util.ParamName.LIMIT;
import static com.epam.esm.util.ParamName.OFFSET;

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
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ResourceNotExistException;
import com.epam.esm.service.OrderService;
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
	private OrderService orderService;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public PageDto<UserDto> find(Map<String, String> params) {
		log.info("FIND User BY PARAMS Service {}", params);
		List<User> userList = userDao.find(params);
		List<UserDto> userDtoList = userList.stream()
				.map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return buildPage(userDtoList, params);
	}
	
	private PageDto<UserDto> buildPage(List<UserDto> userDtoList, Map<String, String> params) {
		int offset = Integer.parseInt(params.get(OFFSET));
		int limit = Integer.parseInt(params.get(LIMIT));
		log.info("Service offset={}, limit={}", offset, limit);
		long totalPositions = userDao.getTotalNumber(params);
		long totalPages = (long) Math.ceil((double) totalPositions / limit);
		long pageNumber = offset / limit + 1;
		log.info("Service page={}", pageNumber);
      return new PageDto<>(userDtoList, totalPages, pageNumber, offset, limit);
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
		return modelMapper.map(user, UserDto.class);
	}
	
	@Override
	@Transactional
	public PageDto<OrderDto> findOrdersByUser(long id, Map<String, String> params) {//TODO new method
		log.info("FIND Order BY User Service userId={}", id);
		User user = new User();
		user.setId(id);
		PageDto<OrderDto> pageOrder = orderService.findOrdersByUser(user, params);
		return pageOrder;
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
