package com.epam.esm.hateoas;

import static com.epam.esm.hateoas.LinkName.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import com.epam.esm.controller.OrderController;
import com.epam.esm.response.OrderResponse;
import com.epam.esm.response.PageOrderResponse;

/**
 * The {@code OrderHateoasUtil} class makes HATEOAS for orders
 * 
 * @author Ihar Klepcha
 */
public class OrderHateoasUtil {

	/**
	 * Adds HATEOAS links
	 * 
	 * @param order {@link OrderResponse} order
	 */
	public static void addLinks(OrderResponse order) {
		order.add(linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel());
	}
	
	/**
	 * Adds HATEOAS links to page
	 * 
	 * @param page {@link PageOrderResponse} page response
	 * @param userId is user id
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 */
	public static void addLinkOnPagedResourceRetrieval(PageOrderResponse page, long userId, 
			Map<String, String> params) {
		int offset = Integer.parseInt(params.get(OFFSET));
		int limit = Integer.parseInt(params.get(LIMIT));
		if (hasNextPage(page.getPageNumber(), page.getTotalPages())) {
			params.put(OFFSET, String.valueOf(offset + limit));
			page.add(linkTo(methodOn(OrderController.class).getOrdersByUser(userId, params)).withRel(NEXT));
		}
		if (hasPreviousPage(page.getPageNumber())) {
			params.put(OFFSET, String.valueOf(offset - limit));
			page.add(linkTo(methodOn(OrderController.class).getOrdersByUser(userId, params)).withRel(PREV));
		}
	}

	private static boolean hasNextPage(long page, long totalPages) {
		return page < totalPages;
	}

	private static boolean hasPreviousPage(long page) {
		return page > 1;
	}
}
