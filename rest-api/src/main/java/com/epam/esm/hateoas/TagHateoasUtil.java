package com.epam.esm.hateoas;

import static com.epam.esm.hateoas.LinkName.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import com.epam.esm.controller.TagController;
import com.epam.esm.response.PageTagResponse;
import com.epam.esm.response.TagResponse;

/**
 * The {@code TagHateoasUtil} class makes HATEOAS for tags
 * 
 * @author Ihar Klepcha
 */
public class TagHateoasUtil {

	/**
	 * Adds HATEOAS links
	 * 
	 * @param tag {@link TagResponse} tag
	 */
	public static void addLinks(TagResponse tag) {
		tag.add(linkTo(methodOn(TagController.class).getTagById(tag.getId())).withSelfRel());
	}

	/**
	 * Adds HATEOAS links to page
	 * 
	 * @param page   {@link PageTagResponse} page response
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 */
	public static void addLinkOnPagedResourceRetrieval(PageTagResponse page, Map<String, String> params) {
		int offset = Integer.parseInt(params.get(OFFSET));
		int limit = Integer.parseInt(params.get(LIMIT));
		if (hasNextPage(page.getPageNumber(), page.getTotalPages())) {
			params.put(OFFSET, String.valueOf(offset + limit));
			page.add(linkTo(methodOn(TagController.class).getAllTags(params)).withRel(NEXT));
		}
		if (hasPreviousPage(page.getPageNumber())) {
			params.put(OFFSET, String.valueOf(offset - limit));
			page.add(linkTo(methodOn(TagController.class).getAllTags(params)).withRel(PREV));
		}
	}

	private static boolean hasNextPage(long page, long totalPages) {
		return page < totalPages;
	}

	private static boolean hasPreviousPage(long page) {
		return page > 1;
	}
}
