package com.epam.esm.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.esm.controller.OrderController;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.response.OrderResponse;

/**
 * The {@code OrderHateoas} class makes HATEOAS for gift сertificates
 * 
 * @author Ihar Klepcha
 */
public class OrderHateoasUtil {
	public static Logger log = LogManager.getLogger();

	/**
	 * Adds HATEOAS links
	 * 
	 * @param orderDto {@link OrderDto} order
	 */
	public static void addLinks(OrderResponse order) {
		order.add(linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel());
	}
}
