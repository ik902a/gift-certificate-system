package com.epam.esm.controller;

import java.util.Map;

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

import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.PageDto;
import com.epam.esm.hateoas.OrderHateoasUtil;
import com.epam.esm.response.OrderResponse;
import com.epam.esm.response.PageOrderResponse;
import com.epam.esm.service.OrderService;

/**
 * The {@code OrderController} class contains endpoint of the API
 * 
 * @author Ihar Klepcha
 */
@Validated
@RestController
@RequestMapping("/users/{userId}/orders")
public class OrderController {
	public static Logger log = LogManager.getLogger();
	@Autowired
	private OrderService orderService;

	 /**
     * Creates new order, processes POST requests at /users/{userId}/orders
     *
     * @param userId is user id
     * @param giftCertificateMap {@link Map} of {@link String} and {@link Integer} data for creating order
     * @return {@link OrderResponse} created order DTO
     */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderResponse createOrder(@Positive  @PathVariable long userId, @RequestBody Map<String, @Positive Integer> giftCertificateMap) {
		OrderDto orderDtoCreated = orderService.create(userId, giftCertificateMap);
		OrderResponse response = OrderResponse.valueOf(orderDtoCreated);
		OrderHateoasUtil.addLinks(response);
		log.info("Controller CREATE Order is worcking");
		return response;
	}
	
	/**
	 * Gets order by user id, processes GET requests at /users/{id}/orders
	 *
	 * @param userId is user id
     * @param giftCertificateMap {@link Map} of {@link String} and {@link String} data for searching orders
	 * @return {@link PageOrderResponse} founded orders
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PageOrderResponse getOrdersByUser(@Positive @PathVariable long userId, @RequestParam Map<String, String> params) {
		PageDto<OrderDto> pageDto = orderService.findOrdersByUser(userId, params);
		PageOrderResponse response = PageOrderResponse.valueOf(pageDto);
		response.getContent().forEach(OrderHateoasUtil::addLinks);
		OrderHateoasUtil.addLinkOnPagedResourceRetrieval(response, userId, params);
		log.info("FIND User DTO by id Controller");
		return response;
	}
	
	/**
	 * Gets order by id, processes GET requests at /users/{userId}/orders/{id}
	 *
	 * @param id is the order id
	 * @return {@link OrderResponse} founded order
	 */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public OrderResponse getOrderById(@Positive @PathVariable long id) {
		OrderDto orderDto = orderService.findById(id);
		OrderResponse response = OrderResponse.valueOf(orderDto);
		OrderHateoasUtil.addLinks(response);
		log.info("FIND Order DTO by id Controller");
		return response;
	}
}
