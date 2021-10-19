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

/**
 * The {@code UserHateoas} class makes HATEOAS for users
 * 
 * @author Ihar Klepcha
 */
public class UserHateoas {
	public static Logger log = LogManager.getLogger();
	
	/**
	 * Adds HATEOAS links
	 * 
	 * @param userDto {@link UserDto} user
	 */
	public static void addLinks(UserDto userDto) {
		userDto.add(linkTo(methodOn(UserController.class).getUserById(userDto.getId())).withSelfRel());
	}

	/**
	 * Adds HATEOAS links to page
	 * 
	 * @param page {@link PageDto} of {@link UserDto} page
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 */
    public static void addLinkOnPagedResourceRetrieval(PageDto<UserDto> page, Map<String, String> params) {
		if (hasNextPage(page.getPageNumber(), page.getTotalPages())) {
			params.put(OFFSET, String.valueOf(page.getOffset() + page.getLimit()));
			page.add(linkTo(methodOn(UserController.class)
					.getAllUsers(params)).withRel(NEXT));
		}
		if (hasPreviousPage(page.getPageNumber())) {
			params.put(OFFSET, String.valueOf(page.getOffset() - page.getLimit()));
			page.add(linkTo(methodOn(UserController.class)
					.getAllUsers(params)).withRel(PREV));
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
