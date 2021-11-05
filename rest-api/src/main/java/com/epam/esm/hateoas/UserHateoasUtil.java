package com.epam.esm.hateoas;

import static com.epam.esm.hateoas.LinkName.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import com.epam.esm.controller.UserController;
import com.epam.esm.response.PageUserResponse;
import com.epam.esm.response.UserResponse;

/**
 * The {@code UserHateoasUtil} class makes HATEOAS for users
 * 
 * @author Ihar Klepcha
 */
public class UserHateoasUtil {
	
	/**
	 * Adds HATEOAS links
	 * 
	 * @param user {@link UserResponse} user
	 */
	public static void addLinks(UserResponse user) {
		user.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
	}

	/**
	 * Adds HATEOAS links to page
	 * 
	 * @param page {@link PageUserResponse} page response
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 */
    public static void addLinkOnPagedResourceRetrieval(PageUserResponse page, Map<String, String> params) {
    	int offset = Integer.parseInt(params.get(OFFSET));
		int limit = Integer.parseInt(params.get(LIMIT));
		if (hasNextPage(page.getPageNumber(), page.getTotalPages())) {
			params.put(OFFSET, String.valueOf(offset + limit));
			page.add(linkTo(methodOn(UserController.class).getAllUsers(params)).withRel(NEXT));
		}
		if (hasPreviousPage(page.getPageNumber())) {
			params.put(OFFSET, String.valueOf(offset - limit));
			page.add(linkTo(methodOn(UserController.class).getAllUsers(params)).withRel(PREV));
		}
	}

	private static boolean hasNextPage(long page, long totalPages) {
		return page < totalPages;
	}

	private static boolean hasPreviousPage(long page) {
		return page > 1;
	}
}
