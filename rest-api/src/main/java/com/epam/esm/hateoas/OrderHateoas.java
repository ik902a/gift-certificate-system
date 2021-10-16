package com.epam.esm.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.esm.controller.OrderController;
import com.epam.esm.controller.UserController;
import com.epam.esm.dto.OrderDto;

public class OrderHateoas {
	
	public static void addLinks(OrderDto orderDto) {
		orderDto.add(linkTo(methodOn(OrderController.class).getOrderById(orderDto.getId())).withSelfRel());
		orderDto.getUser().add(
				linkTo(methodOn(UserController.class).getUserById(orderDto.getUser().getId())).withSelfRel());
	}
}
