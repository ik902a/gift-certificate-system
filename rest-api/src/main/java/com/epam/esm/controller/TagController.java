package com.epam.esm.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;

/**
 * The {@code TagController} class contains endpoint of the API
 * 
 * @author Ihar Klepcha
 */
@RestController
@RequestMapping("/tags")
public class TagController {
	public static Logger log = LogManager.getLogger();
	@Autowired
	private TagService tagService;

    /**
     * Creates new tag, processes POST requests at /tags
     *
     * @param tag {@link Tag} tag
     * @return {@link Tag}  created tag
     */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Tag createTag(@RequestBody Tag tag) {
		log.info("Post mapping Tag");
		Tag tagCreated = tagService.create(tag);
		return tagCreated;
	}

	/**
     * Gets tags, processes GET requests at /tags
     *
     * @return {@link List} of {@link Tag} founded tags
     */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Tag> getAllTags() {
		log.info("Get mapping Tag");
		List<Tag> tags = tagService.findAll();
		return tags;
	}

	/**
     * Gets tag by id, processes GET requests at /tags/{id}
     *
     * @param id is the tag id
     * @return {@link Tag} founded tag
     */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Tag getTag(@PathVariable long id) {
		Tag tag = tagService.findById(id);
		return tag;
	}

	/**
     * Deletes tag by id, processes DELETE requests at /tags/{id}
     *
     * @param id is the tag id
     */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTag(@PathVariable long id) {
		tagService.delete(id);
	}
}
