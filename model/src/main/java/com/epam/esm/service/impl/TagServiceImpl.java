package com.epam.esm.service.impl;

import static com.epam.esm.dao.util.ParamName.LIMIT;
import static com.epam.esm.dao.util.ParamName.OFFSET;
import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
 * The {@code TagServiceImpl} class is responsible for operations with the car
 * 
 * @author Ihar Klepcha
 * @see TagService
 */
@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private TagDao tagDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public TagDto create(TagDto tagDto) {
		Tag tag = modelMapper.map(tagDto, Tag.class);
		tag = tagDao.create(tag);
		return modelMapper.map(tag, TagDto.class);
	}

	@Override
	@Transactional
	public PageDto<TagDto> find(Map<String, String> params) {
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
		Optional<Tag> tagOptional = tagDao.findEntityById(id);
		Tag tag = tagOptional.orElseThrow(
			() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
					, String.valueOf(id)
					, TAG_INCORRECT.getErrorCode()));
		return modelMapper.map(tag, TagDto.class);
	}

	@Override
	@Transactional
	public void delete(long id) {
		if (!tagDao.delete(id)) {
			throw new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
					, String.valueOf(id)
					, TAG_INCORRECT.getErrorCode());
		}
	}

	@Override
	public TagDto findMostPopularTagOfUserWithHighestCostOfAllOrders() {
		Optional<Tag> tagOptional = tagDao.findMostPopularTagOfUserWithHighestCostOfAllOrders();
		Tag tag = tagOptional.orElseThrow(
				() -> new ResourceNotExistException(NOT_FOUND_POPULAR_TAG.getErrorMessageKey()
						, TAG_INCORRECT.getErrorCode()));
		return modelMapper.map(tag, TagDto.class);
	}
}
