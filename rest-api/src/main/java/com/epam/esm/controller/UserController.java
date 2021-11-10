package com.epam.esm.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.hateoas.UserHateoasUtil;
import com.epam.esm.response.PageUserResponse;
import com.epam.esm.response.UserResponse;
import com.epam.esm.service.UserService;

/**
 * The {@code UserController} class contains endpoint of the API
 * 
 * @author Ihar Klepcha
 */
@Validated
@RestController
@RequestMapping("/users")
public class UserController {
	public static Logger log = LogManager.getLogger();
	@Autowired
	private UserService userService;

	/**
	 * Gets users by params, processes GET requests at /users
	 *
	 * @param params {@link Map} of {@link String} and {@link String} data for searching users
	 * @return {@link PageUserResponse} founded users
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public PageUserResponse getAllUsers(@RequestParam Map<String, String> params) {
		log.info("Finding Users with parameters");
		PageDto<UserDto> pageDto = userService.find(params);
		PageUserResponse response = PageUserResponse.valueOf(pageDto);
		response.getContent().forEach(UserHateoasUtil::addLinks);
		UserHateoasUtil.addLinkOnPagedResourceRetrieval(response, params);
		return response;
	}	

	/**
	 * Gets user by id, processes GET requests at /users/{id}
	 *
	 * @param id is the user id
	 * @return {@link UserResponse} founded user
	 */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public UserResponse getUserById(@Positive @PathVariable long id) {
		log.info("Finding User by id");
		UserDto userDto = userService.findById(id);
		UserResponse response = UserResponse.valueOf(userDto);
		UserHateoasUtil.addLinks(response);
		return response;
	}	


}
