package com.epam.esm.hateoas;

import static com.epam.esm.hateoas.LinkName.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.response.UserResponse;

/**
 * The {@code UserHateoas} class makes HATEOAS for users
 * 
 * @author Ihar Klepcha
 */
public class UserHateoasUtil {
	public static Logger log = LogManager.getLogger();
	
	/**
	 * Adds HATEOAS links
	 * 
	 * @param userDto {@link UserDto} user
	 */
	public static void addLinks(UserResponse user) {
		user.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
	}

//	/**
//	 * Adds HATEOAS links to page
//	 * 
//	 * @param page {@link PageDto} of {@link UserDto} page
//	 * @param params {@link Map} of {@link String} and {@link String} parameters
//	 */
//    public static void addLinkOnPagedResourceRetrieval(PageUserResponse page, Map<String, String> params) {
//    	int offset = Integer.parseInt(params.get(OFFSET));
//		int limit = Integer.parseInt(params.get(LIMIT));
//		if (hasNextPage(page.getPageNumber(), page.getTotalPages())) {
//			params.put(OFFSET, String.valueOf(offset + limit));
//			page.add(linkTo(methodOn(UserController.class)
//					.getAllUsers(params)).withRel(NEXT));
//		}
//		if (hasPreviousPage(page.getPageNumber())) {
//			params.put(OFFSET, String.valueOf(offset - limit));
//			page.add(linkTo(methodOn(UserController.class)
//					.getAllUsers(params)).withRel(PREV));
//		}
//	}
//
//	private static boolean hasNextPage(long page, long totalPages) {
//		log.info("hasNextPage page={}, total={}", page, totalPages);
//		return page < totalPages;
//	}
//
//	private static boolean hasPreviousPage(long page) {
//		log.info("hasPreviousPage page={}", page);
//		return page > 1;
//	}
}
