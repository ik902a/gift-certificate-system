package com.epam.esm.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.hateoas.TagHateoasUtil;
import com.epam.esm.response.PageTagResponse;
import com.epam.esm.response.TagResponse;
import com.epam.esm.service.TagService;

/**
 * The {@code TagController} class contains endpoint of the API
 * 
 * @author Ihar Klepcha
 */
@Validated
@RestController
@RequestMapping("/tags")
public class TagController {
	public static Logger log = LogManager.getLogger();
	private TagService tagService;

	/**
	 * Constructs a tag controller
	 * 
	 * @param tagService {@link TagService} service for tag
	 */
	@Autowired
	public TagController(TagService tagService) {
		super();
		this.tagService = tagService;
	}

	/**
	 * Creates new tag, processes POST requests at /tags
	 *
	 * @param tagDto {@link TagDto} tag
	 * @return {@link TagResponse} created tag
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ADMIN')")
	public TagResponse createTag(@Valid @RequestBody TagDto tagDto) {
		log.info("Creating Tag");
		TagDto tagDtoCreated = tagService.create(tagDto);
		TagResponse response = TagResponse.valueOf(tagDtoCreated);
		TagHateoasUtil.addLinks(response);
		return response;
	}

	/**
	 * Gets tags, processes GET requests at /tags
	 *
	 * @param params {@link Map} of {@link String} and {@link String} data for
	 *               searching tags
	 * @return {@link PageTagResponse} founded tags
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public PageTagResponse getAllTags(@RequestParam Map<String, String> params) {
		log.info("Finding Tags with parameters");
		PageDto<TagDto> pageDto = tagService.find(params);
		PageTagResponse response = PageTagResponse.valueOf(pageDto);
		response.getContent().forEach(TagHateoasUtil::addLinks);
		TagHateoasUtil.addLinkOnPagedResourceRetrieval(response, params);
		return response;
	}

	/**
	 * Gets tag by id, processes GET requests at /tags/{id}
	 *
	 * @param id is the tag id
	 * @return {@link TagResponse} founded tag
	 */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public TagResponse getTagById(@Positive @PathVariable long id) {
		log.info("Finding Tag by id");
		TagDto tagDto = tagService.findById(id);
		TagResponse response = TagResponse.valueOf(tagDto);
		TagHateoasUtil.addLinks(response);
		return response;
	}

	/**
	 * Deletes tag by id, processes DELETE requests at /tags/{id}
	 *
	 * @param id is the tag id
	 * @return {@link ResponseEntity} response
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteTag(@Positive @PathVariable long id) {
		log.info("Deleting Tag by id");
		tagService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Gets the most widely used tag of a user with the highest cost of all orders
	 * at /tags/popular
	 *
	 * @return {@link TagResponse} founded tag
	 * 
	 */
	@GetMapping("/popular")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public TagResponse getMostPopularTagOfUserWithHighestCostOfAllOrders() {
		log.info("Finding the most widely used Tag of a user with the highest cost of all orders");
		TagDto tagDto = tagService.findMostPopularTagOfUserWithHighestCostOfAllOrders();
		TagResponse response = TagResponse.valueOf(tagDto);
		TagHateoasUtil.addLinks(response);
		return response;
	}
}
