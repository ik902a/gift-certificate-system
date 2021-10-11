package com.epam.esm.service.impl;

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
//import com.epam.esm.validator.TagValidator;

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
//	@Override
//	@Transactional
//	public Tag create(Tag tag) {
//		String tagName = tag.getName();
////		TagValidator.validateName(tagName);
////		if (tagDao.findEntityByName(tagName).isPresent()) {
////			throw new ResourceNotExistException(NAME_EXIST.getErrorMessageKey(), 
////					tagName, 
////					TAG_INCORRECT_DATA.getErrorCode());
////		}
//		tag = tagDao.create(tag);
//		return tag;
//	}

	@Override
	@Transactional
	public PageDto<TagDto> find(Map<String, String> params) {
		List<Tag> tagList = tagDao.find(params);
		List<TagDto> tagDtoList = tagList.stream()
				.map(tag -> modelMapper.map(tag, TagDto.class))
				.collect(Collectors.toList());
		long totalPositions = tagDao.getTotalNumber(params);
		return new PageDto<>(tagDtoList, totalPositions);
	}
	
	@Override
	@Transactional
	public TagDto findById(long id) {
		Optional<Tag> tagOptional = tagDao.findEntityById(id);
		Tag tag = tagOptional.orElseThrow(
			() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
					, id
					, TAG_INCORRECT.getErrorCode()));
		return modelMapper.map(tag, TagDto.class);
	}

	@Override
	@Transactional
	public void delete(long id) {
		if (!tagDao.delete(id)) {
			throw new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
					, id
					, TAG_INCORRECT.getErrorCode());
		}
	}
}
