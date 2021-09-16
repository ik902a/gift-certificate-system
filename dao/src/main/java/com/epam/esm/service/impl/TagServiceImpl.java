package com.epam.esm.service.impl;

import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceException;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagDao tagDao;

	@Override
	@Transactional
	public Tag create(Tag tag) {
		String tagName = tag.getName();
		TagValidator.validateName(tagName);
		if (tagDao.findEntityByName(tagName).isPresent()) {
			throw new ResourceException(NAME_EXIST.getErrorMessageKey(), 
					tagName, 
					TAG_INCORRECT_DATA.getErrorCode());
		}
		tag = tagDao.create(tag);
		return tag;
	}

	@Override
	@Transactional
	public List<Tag> findAll() {
		List<Tag> tagList = tagDao.findAll();
		return tagList;
	}

	@Override
	@Transactional
	public Tag findById(long id) {
		TagValidator.validateId(id);
		Optional<Tag> tagOptional = tagDao.findEntityById(id);
		Tag tag = tagOptional.orElseThrow(() -> new ResourceException(
				RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey(),
				String.valueOf(id), 
				TAG_INCORRECT_ID.getErrorCode()));
		return tag;
	}

	@Override
	@Transactional
	public void delete(long id) {
		TagValidator.validateId(id);
		if (!tagDao.delete(id)) {
			throw new ResourceException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey(), 
					String.valueOf(id),
					TAG_INCORRECT_ID.getErrorCode());
		}
	}
}
