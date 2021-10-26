package com.epam.esm.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.esm.dto.OrderDataDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.hateoas.OrderHateoasUtil;
import com.epam.esm.response.OrderResponse;
import com.epam.esm.service.OrderService;

/**
 * The {@code OrderController} class contains endpoint of the API
 * 
 * @author Ihar Klepcha
 */
@Validated
@RestController
@RequestMapping("/orders")
public class OrderController {// TODO Depricate
	public static Logger log = LogManager.getLogger();
	@Autowired
	private OrderService orderService;

//    /**
//     * Creates new order, processes POST requests at /orders
//     *
//     * @param orderDto {@link OrderDto} order DTO
//     * @return {@link OrderDto} created order DTO
//     */
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public OrderResponse createOrder(@Valid @RequestBody OrderDataDto orderDataDto) {
//		OrderDto orderDtoCreated = orderService.create(orderDataDto);
//		OrderResponse response = OrderResponse.valueOf(orderDtoCreated);
//		OrderHateoasUtil.addLinks(response);
//		log.info("Controller CREATE Order is worcking");
//		return response;
//	}
	
//	/**
//	 * Gets order by id, processes GET requests at /orders/{id}
//	 *
//	 * @param id is the order id
//	 * @return {@link OrderDto} founded order
//	 */
//	@GetMapping("/{id}")
//	@ResponseStatus(HttpStatus.OK)
//	public OrderResponse getOrderById(@Positive @PathVariable long id) {
//		OrderDto orderDto = orderService.findById(id);
//		OrderResponse response = OrderResponse.valueOf(orderDto);
//		OrderHateoasUtil.addLinks(response);
//		log.info("FIND Order DTO by id Controller");
//		return response;
//	}
}
