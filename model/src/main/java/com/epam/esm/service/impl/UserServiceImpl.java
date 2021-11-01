package com.epam.esm.service.impl;

import static com.epam.esm.dao.util.ParamName.LIMIT;
import static com.epam.esm.dao.util.ParamName.OFFSET;
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
		log.info("Finding User with searching parametres: {}", params);
		List<User> userList = userDao.find(params);
		List<UserDto> userDtoList = userList.stream()
				.map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return buildPage(userDtoList, params);
	}
	
	private PageDto<UserDto> buildPage(List<UserDto> userDtoList, Map<String, String> params) {
		int offset = Integer.parseInt(params.get(OFFSET));
		int limit = Integer.parseInt(params.get(LIMIT));
		long totalPositions = userDao.getTotalNumber(params);
		long totalPages = (long) Math.ceil((double) totalPositions / limit);
		long pageNumber = offset / limit + 1;
      return new PageDto<>(userDtoList, totalPages, pageNumber, offset, limit);
	}

	@Override
	@Transactional
	public UserDto findById(long id) {
		log.info("Finding User by id={}", id);
		Optional<User> userOptional = userDao.findEntityById(id);
		User user = userOptional.orElseThrow(
			() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
					, String.valueOf(id)
					, USER_INCORRECT.getErrorCode()));
		return modelMapper.map(user, UserDto.class);
	}
	
	@Override
	@Transactional
	public UserDto create(UserDto userDto) {
		log.info("Creating user from {}", userDto);
		User user = modelMapper.map(userDto, User.class);
		user = userDao.create(user);
		return modelMapper.map(user, UserDto.class);
	}
}
