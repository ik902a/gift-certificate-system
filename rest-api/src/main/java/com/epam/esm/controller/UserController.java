package com.epam.esm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.esm.dto.UserDto;
import com.epam.esm.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	public static Logger log = LogManager.getLogger();
	@Autowired
	private UserService userService;

	/**
	 * Gets users by params, processes GET requests at /users
	 *
	 * @param params {@link Map} of {@link String} and {@link String} data for
	 *               searching users
	 * @return {@link List} of {@link UserDto} founded users
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UserDto> getUsers(@RequestParam Map<String, String> params) {
		List<UserDto> userDtoList = userService.find(params);
		log.info("FIND User DTO Controller {}", userDtoList.get(0).toString());
		return userDtoList;
	}

	/**
	 * Gets user by id, processes GET requests at /users/{id}
	 *
	 * @param id is the user id
	 * @return {@link UserDto} founded user
	 */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDto getUserById(@PathVariable long id) {
		UserDto userDto = userService.findById(id);
		return userDto;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto createUser(@RequestBody @Valid UserDto userDto) {
		UserDto userDtoCreated = userService.create(userDto);
		log.info("Controller CREATE User is worcking");
		return userDtoCreated;
	}
}
