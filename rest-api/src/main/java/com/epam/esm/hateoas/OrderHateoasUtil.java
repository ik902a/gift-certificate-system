package com.epam.esm.hateoas;

import static com.epam.esm.hateoas.LinkName.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.esm.controller.OrderController;
import com.epam.esm.controller.UserController;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.response.OrderResponse;
import com.epam.esm.response.PageOrderResponse;

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
	
	public static void addLinkOnPagedResourceRetrieval(PageOrderResponse page, long id, Map<String, String> params) {
		int offset = Integer.parseInt(params.get(OFFSET));
		int limit = Integer.parseInt(params.get(LIMIT));
		if (hasNextPage(page.getPageNumber(), page.getTotalPages())) {
			params.put(OFFSET, String.valueOf(offset + limit));
			page.add(linkTo(methodOn(UserController.class).getOrdersByUser(id, params)).withRel(NEXT));
		}
		if (hasPreviousPage(page.getPageNumber())) {
			params.put(OFFSET, String.valueOf(offset - limit));
			page.add(linkTo(methodOn(UserController.class).getOrdersByUser(id, params)).withRel(PREV));
		}
	}

	private static boolean hasNextPage(long page, long totalPages) {
		log.info("hasNextPage page={}, total={}", page, totalPages);
		return page < totalPages;
	}

	private static boolean hasPreviousPage(long page) {
		log.info("hasPreviousPage page={}", page);
		return page > 1;
	}
}
