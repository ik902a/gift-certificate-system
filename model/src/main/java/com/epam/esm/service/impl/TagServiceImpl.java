package com.epam.esm.service.impl;

//import static com.epam.esm.exception.ErrorCode.*;
//import static com.epam.esm.exception.ErrorMessageKey.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
//import com.epam.esm.exception.ResourceNotExistException;
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
	public List<TagDto> findAll() {
		List<Tag> tagList = tagDao.findAll();
		 List<TagDto> tagDtoList = tagList.stream()
	                .map(tag -> modelMapper.map(tag, TagDto.class))
	                .collect(Collectors.toList());
		return tagDtoList;
	}

	@Override
	@Transactional
	public TagDto findById(long id) {
		Tag tag = tagDao.findEntityById(id);
		return modelMapper.map(tag, TagDto.class);
	}
//	@Override
//	@Transactional
//	public Tag findById(long id) {
////		TagValidator.validateId(id);
//		Tag tag = tagDao.findEntityById(id);
////		Tag tag = tagOptional.orElseThrow(
////				() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey(),
////				String.valueOf(id), 
////				TAG_INCORRECT_ID.getErrorCode()));
//		return tag;
//	}

	@Override
	@Transactional
	public void delete(long id) {
		tagDao.delete(id);
	}
//	@Override
//	@Transactional
//	public void delete(long id) {
////		TagValidator.validateId(id);
//		if (!tagDao.delete(id)) {
////			throw new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey(), 
////					String.valueOf(id),
////					TAG_INCORRECT_ID.getErrorCode());
//		}
//	}
}
