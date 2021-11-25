package com.epam.esm.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.UserDetailsImpl;
import com.epam.esm.entity.User;

/**
 * The {@code UserDetailsServiceImpl} is class for loading user data
 * 
 * @author Ihar Klepcha
 * @see UserDetailsService
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	public static Logger log = LogManager.getLogger();
	private UserDao userDao;

	/**
	 * Constructs service for searching user data
	 * 
	 * @param userDao {@link UserDao} DAO for user
	 */
	@Autowired
	public UserDetailsServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Logining user from {}", username);
		User user = userDao.findEntityByName(username)
				.orElseThrow(
						() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);
	}
}
