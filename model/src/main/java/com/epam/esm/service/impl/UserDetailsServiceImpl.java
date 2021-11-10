package com.epam.esm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.UserDetailsImpl;
import com.epam.esm.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user  = userDao.findEntityByName(username)
				.orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with username: " + username));// TODO Localization 
		return UserDetailsImpl.build(user);
		}
}

