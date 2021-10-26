package com.epam.esm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.esm.dto.OrderDataDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.hateoas.OrderHateoasUtil;
import com.epam.esm.hateoas.UserHateoasUtil;
import com.epam.esm.response.OrderResponse;
import com.epam.esm.response.PageOrderResponse;
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
	 * @return {@link List} of {@link UserDto} founded users
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PageUserResponse getAllUsers(@Valid @RequestParam Map<String, String> params) {
		PageDto<UserDto> pageDto = userService.find(params);
		
		PageUserResponse response = PageUserResponse.valueOf(pageDto);
		response.getContent().forEach(UserHateoasUtil::addLinks);
		UserHateoasUtil.addLinkOnPagedResourceRetrieval(response, params);
		log.info("FIND User DTO Controller");
		return response;
	}	

	/**
	 * Gets user by id, processes GET requests at /users/{id}
	 *
	 * @param id is the user id
	 * @return {@link UserDto} founded user
	 */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse getUserById(@Positive @PathVariable long id) {
		UserDto userDto = userService.findById(id);
		UserResponse response = UserResponse.valueOf(userDto);
		UserHateoasUtil.addLinks(response);
		log.info("FIND User DTO by id Controller");
		return response;
	}	
	
    /**
     * Creates new order, processes POST requests at /orders
     *
     * @param orderDto {@link OrderDto} order DTO
     * @return {@link OrderDto} created order DTO
     */
	@PostMapping("/{id}/orders")// TODO new method
	@ResponseStatus(HttpStatus.CREATED)
	public OrderResponse createOrder(@Positive @PathVariable long id, 
//			@Valid @RequestBody OrderDataDto orderDataDto) {
			@RequestBody Map<Long, Integer> giftCertificateMap) {
		log.info("Controller CREATE Order is worcking----------------------------------------");
		
		OrderDto orderDtoCreated = userService.createOrder(id, giftCertificateMap
//				orderDataDto.getGiftCertificateMap()
				);
		
		OrderResponse response = OrderResponse.valueOf(orderDtoCreated);
		OrderHateoasUtil.addLinks(response);
		log.info("Controller CREATE Order is worcking");
		return response;
	}
	
	/**
	 * Gets order by user id, processes GET requests at /users/{id}
	 *
	 * @param id is the user id
	 * @return {@link UserDto} founded user
	 */
	@GetMapping("/{id}/orders")// TODO new method
	@ResponseStatus(HttpStatus.OK)
	public PageOrderResponse getOrdersByUser(@Positive @PathVariable long id, @Valid @RequestParam Map<String, String> params) {
		PageDto<OrderDto> pageDto = userService.findOrdersByUser(id, params);
		
		PageOrderResponse response = PageOrderResponse.valueOf(pageDto);
		response.getContent().forEach(OrderHateoasUtil::addLinks);
		OrderHateoasUtil.addLinkOnPagedResourceRetrieval(response, id, params);
		log.info("FIND User DTO by id Controller");
		return response;
	}
	
	/**
	 * Gets order by id, processes GET requests at /orders/{id}
	 *
	 * @param id is the order id
	 * @return {@link OrderDto} founded order
	 */
	@GetMapping("/*/orders/{id}")
	@ResponseStatus(HttpStatus.OK)// TODO new method
	public OrderResponse getOrderById(@Positive @PathVariable long id) {
		OrderDto orderDto = userService.findOrderById(id);
		OrderResponse response = OrderResponse.valueOf(orderDto);
		OrderHateoasUtil.addLinks(response);
		log.info("FIND Order DTO by id Controller");
		return response;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse createUser(@RequestBody @Valid UserDto userDto) {
		UserDto userDtoCreated = userService.create(userDto);
		UserResponse response = UserResponse.valueOf(userDtoCreated);
		log.info("Controller CREATE User is worcking");
		return response;
	}
}
