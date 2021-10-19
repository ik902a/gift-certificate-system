package com.epam.esm.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@Autowired
	private TagService tagService;

    /**
     * Creates new tag, processes POST requests at /tags
     *
     * @param tagDto {@link TagDto} tag
     * @return {@link TagDto} created tag
     */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TagResponse createTag(@Valid @RequestBody TagDto tagDto) {
		TagDto tagDtoCreated = tagService.create(tagDto);
		log.info("CREATE Tag DTO Controller");
		TagResponse response = TagResponse.valueOf(tagDtoCreated);
		TagHateoasUtil.addLinks(response);
		return response;
	}

	/**
     * Gets tags, processes GET requests at /tags
     *
     * @return {@link PageDto} of {@link TagDto} founded tags
     */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PageTagResponse getAllTags(@Valid @RequestParam Map<String, String> params) {
		PageDto<TagDto> pageDto = tagService.find(params);
		log.info("FIND all Tag DTO Controller");
		PageTagResponse response = PageTagResponse.valueOf(pageDto);
		response.getContent().forEach(TagHateoasUtil::addLinks);
		TagHateoasUtil.addLinkOnPagedResourceRetrieval(response, params);
		return response;
	}

	/**
     * Gets tag by id, processes GET requests at /tags/{id}
     *
     * @param id is the tag id
     * @return {@link TagDto} founded tag
     */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TagResponse getTagById(@Positive @PathVariable long id) {
		TagDto tagDto = tagService.findById(id);
		TagResponse response = TagResponse.valueOf(tagDto);
		TagHateoasUtil.addLinks(response);
		log.info("FIND Tag DTO by id Controller");
		return response;
	}

	/**
     * Deletes tag by id, processes DELETE requests at /tags/{id}
     *
     * @param id is the tag id
     */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteTag(@Positive @PathVariable long id) {
		tagService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
     * Gets the most widely used tag of a user with the highest cost of all orders.
     *
     * @return {@link TagDto} founded tag
     */
    @GetMapping("/popular")
    @ResponseStatus(HttpStatus.OK)
    public TagResponse getMostPopularTagOfUserWithHighestCostOfAllOrders() {
        TagDto tagDto = tagService.findMostPopularTagOfUserWithHighestCostOfAllOrders();
        TagResponse response = TagResponse.valueOf(tagDto);
        TagHateoasUtil.addLinks(response);
        return response;
    }
}
