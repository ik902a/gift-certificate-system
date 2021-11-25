package com.epam.esm.service.impl;

import static com.epam.esm.dao.util.ParamName.LIMIT;
import static com.epam.esm.dao.util.ParamName.OFFSET;
import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotExistException;
import com.epam.esm.service.TagService;

/**
 * The {@code TagServiceImpl} class is responsible for operations with the tag
 * 
 * @author Ihar Klepcha
 * @see TagService
 */
@Service
public class TagServiceImpl implements TagService {
	public static Logger log = LogManager.getLogger();
	private TagDao tagDao;
	private ModelMapper modelMapper;

	/**
	 * Constructs service for tag
	 * 
	 * @param tagDao      {@link TagDao} DAO for tag
	 * @param ModelMapper {@link ModelMapper} performs object mapping
	 */
	@Autowired
	public TagServiceImpl(TagDao tagDao, ModelMapper modelMapper) {
		super();
		this.tagDao = tagDao;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional
	public TagDto create(TagDto tagDto) {
		log.info("Creating Tag from {}", tagDto);
		Tag tag = modelMapper.map(tagDto, Tag.class);
		tag = tagDao.create(tag);
		return modelMapper.map(tag, TagDto.class);
	}

	@Override
	@Transactional
	public PageDto<TagDto> find(Map<String, String> params) {
		log.info("Finding Tag with searching parametres: {}", params);
		List<Tag> tagList = tagDao.find(params);
		List<TagDto> tagDtoList = tagList.stream()
				.map(tag -> modelMapper.map(tag, TagDto.class))
				.collect(Collectors.toList());
		return buildPage(tagDtoList, params);
	}

	private PageDto<TagDto> buildPage(List<TagDto> tagDtoList, Map<String, String> params) {
		int offset = Integer.parseInt(params.get(OFFSET));
		int limit = Integer.parseInt(params.get(LIMIT));
		long totalPositions = tagDao.getTotalNumber(params);
		long totalPages = (long) Math.ceil((double) totalPositions / limit);
		long pageNumber = offset / limit + 1;
		return new PageDto<>(tagDtoList, totalPages, pageNumber, offset, limit);
	}

	@Override
	@Transactional
	public TagDto findById(long id) {
		log.info("Finding Tag by id={}", id);
		Tag tag = tagDao.findEntityById(id)
				.orElseThrow(() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID, 
						String.valueOf(id), 
						TAG_INCORRECT));
		return modelMapper.map(tag, TagDto.class);
	}

	@Override
	@Transactional
	public void delete(long id) {
		log.info("Deleting Tag by id={}", id);
		if (!tagDao.delete(id)) {
			throw new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID, String.valueOf(id), TAG_INCORRECT);
		}
	}

	@Override
	@Transactional
	public TagDto findMostPopularTagOfUserWithHighestCostOfAllOrders() {
		log.info("Finding the most widely used Tag of a user with the highest cost of all orders");
		Tag tag = tagDao.findMostPopularTagOfUserWithHighestCostOfAllOrders()
				.orElseThrow(() -> new ResourceNotExistException(NOT_FOUND_POPULAR_TAG, TAG_INCORRECT));
		return modelMapper.map(tag, TagDto.class);
	}
}
