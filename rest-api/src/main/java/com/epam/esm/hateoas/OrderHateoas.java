package com.epam.esm.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.esm.controller.OrderController;
import com.epam.esm.dto.OrderDto;

/**
 * The {@code OrderHateoas} class makes HATEOAS for gift сertificates
 * 
 * @author Ihar Klepcha
 */
public class OrderHateoas {
	public static Logger log = LogManager.getLogger();

	public static void addLinks(OrderDto orderDto) {
		orderDto.add(linkTo(methodOn(OrderController.class).getOrderById(orderDto.getId())).withSelfRel());
	}
}
