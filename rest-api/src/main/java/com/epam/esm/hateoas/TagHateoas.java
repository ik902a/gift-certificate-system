package com.epam.esm.hateoas;

import static com.epam.esm.hateoas.LinkName.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.esm.controller.TagController;
import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.TagDto;

/**
 * The {@code TagHateoas} class makes HATEOAS for tags
 * 
 * @author Ihar Klepcha
 */
public class TagHateoas {
	public static Logger log = LogManager.getLogger();
    
	/**
	 * Adds HATEOAS links
	 * 
	 * @param tagDto {@link TagDto} tag
	 */
    public static void addLinks(TagDto tagDto) {
        tagDto.add(linkTo(methodOn(TagController.class).getTagById(tagDto.getId())).withSelfRel());
        tagDto.add(linkTo(methodOn(TagController.class).deleteTag(tagDto.getId())).withRel(DELETE));
    }
    
	/**
	 * Adds HATEOAS links to page
	 * 
	 * @param page {@link PageDto} of {@link TagDto} page
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 */
	public static void addLinkOnPagedResourceRetrieval(PageDto<TagDto> page, Map<String, String> params) {
		if (hasNextPage(page.getPageNumber(), page.getTotalPages())) {
			params.put(OFFSET, String.valueOf(page.getOffset() + page.getLimit()));
			page.add(linkTo(methodOn(TagController.class).getAllTags(params)).withRel(NEXT));
		}
		if (hasPreviousPage(page.getPageNumber())) {
			params.put(OFFSET, String.valueOf(page.getOffset() - page.getLimit()));
			page.add(linkTo(methodOn(TagController.class).getAllTags(params)).withRel(PREV));
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
