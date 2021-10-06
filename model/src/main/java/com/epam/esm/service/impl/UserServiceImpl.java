package com.epam.esm.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
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
	public List<UserDto> find(Map<String, String> params) {
		log.info("FIND User BY PARAMS Service {}", params);
		List<User> userList = userDao.find(params);
	    List<UserDto> userDtoList = userList.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	@Transactional
	public UserDto findById(long id) {
		log.info("FIND User BY ID Service id={}", id);
		User user = userDao.findEntityById(id);
		return modelMapper.map(user, UserDto.class);
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
