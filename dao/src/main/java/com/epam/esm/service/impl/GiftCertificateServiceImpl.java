package com.epam.esm.service.impl;

import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.EnumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.ParamName;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ErrorCode;
import com.epam.esm.exception.ErrorMessageKey;
import com.epam.esm.exception.ParamException;
import com.epam.esm.exception.ResourceException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.validator.GiftCertificateValidator;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
	public static Logger log = LogManager.getLogger();
	@Autowired
	private GiftCertificateDao giftCertificateDao;
	@Autowired
	private TagDao tagDao;
	
	
	
//	@Autowired
//	public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagDao tagDao) {
//		super();
//		this.giftCertificateDao = giftCertificateDao;
//		this.tagDao = tagDao;
//	}

	@Override
	@Transactional
	public GiftCertificate create(GiftCertificate giftCertificate) {
		log.info("CREATE {}", giftCertificate);
		GiftCertificateValidator.validateGiftCertificate(giftCertificate);
		containsName(giftCertificate.getName());// Check if DB contains Name
		ZonedDateTime currentDate = ZonedDateTime.now();
		giftCertificate.setCreateDate(currentDate);
		giftCertificate.setLastUpdateDate(currentDate);
		giftCertificate = giftCertificateDao.create(giftCertificate);
		if (giftCertificate.getTags() != null) {
			createGiftCertificateTag(giftCertificate);
		}
		return giftCertificate;
	}
	
	private void createGiftCertificateTag(GiftCertificate giftCertificate) {
		List<Tag> tags = giftCertificate.getTags();
		tags.forEach(tag -> tag.setId(
				tagDao.findEntityByName(tag.getName()).orElseGet(() -> tagDao.create(tag)).getId()));
		giftCertificateDao.createGiftCertificateTag(giftCertificate);
	}

//	@Override
//	@Transactional
//	public List<GiftCertificate> findAll() {
//		List<GiftCertificate> giftCertificateList = giftCertificateDao.findAll();
//		return giftCertificateList;
//	}
	
	@Override
	@Transactional
	public List<GiftCertificate> findAll(Map<String, String> params) {
		log.debug("Params ---------------{}", params.toString());
		List<GiftCertificate> giftCertificateList;
		if (params.isEmpty()) {
			giftCertificateList = giftCertificateDao.findAll();
		} else {
//			String orderBy = params.containsKey() ? params.get("order_by") : "asc";
//			params.remove("order_by");
			
			String orderBy = params.getOrDefault("order_by", "asc");// TODO подправить
			
			Optional<String> param = params.keySet()
					.stream()
					.filter(p -> EnumUtils.isValidEnum(ParamName.class, p))
					.findFirst();
			
			if (param.isPresent()) {
				log.debug("Param ---------------{}", param.get().toString());
			switch (ParamName.valueOf(params.get(param.get()).toUpperCase())) {
	           case  TAG:
	        	   giftCertificateList = giftCertificateDao.findEntityByTagName(param.get(), orderBy);
	               break;
	           case NAME:
	        	   giftCertificateList = giftCertificateDao.findEntityByPartName(param.get(), orderBy);
	               break;
	           case DESCRIPTION:
	        	   giftCertificateList =giftCertificateDao.findEntityByPartDescription(param.get(), orderBy);
	               break;
	           default:
	        	   throw new ParamException(ErrorMessageKey.INCORRECT_PARAM.getErrorMessageKey(), 
	   					ErrorCode.INCORRECT_PARAM.getErrorCode());
			}
		} else {
			throw new ParamException(ErrorMessageKey.INCORRECT_PARAM.getErrorMessageKey(), 
					ErrorCode.INCORRECT_PARAM.getErrorCode());
		}
		}
		return giftCertificateList;
	}
	

	@Override
	@Transactional
	public GiftCertificate findById(long id) {
		GiftCertificateValidator.validateId(id);
		Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findEntityById(id);
		GiftCertificate giftCertificate;
		if (giftCertificateOptional.isPresent()) {
			giftCertificate = giftCertificateOptional.get();
		} else {
			throw new ResourceException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey(), 
					String.valueOf(id),
					GIFT_CERTIFICATE_INCORRECT_ID.getErrorCode());
		}
		return giftCertificate;
	}

	@Override
	@Transactional
	public GiftCertificate update(GiftCertificate giftCertificate) {
		GiftCertificate giftCertificateOld = findById(giftCertificate.getId());
		String newName = giftCertificate.getName();
		if (!newName.equals(giftCertificateOld.getName())) {
			containsName(newName);
		}
		updateFields(giftCertificateOld, giftCertificate);
		GiftCertificateValidator.validateGiftCertificate(giftCertificate);
		updateGiftCertificateTag(giftCertificateOld, giftCertificate);
		giftCertificate = giftCertificateDao.update(giftCertificate);
		return giftCertificate;
	}
	
	@Override
	@Transactional
	public void delete(long id) {
		GiftCertificateValidator.validateId(id);
		if (!giftCertificateDao.delete(id)) {
			throw new ResourceException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey(), String.valueOf(id),
					GIFT_CERTIFICATE_INCORRECT_ID.getErrorCode());
		}
	}
	
	@Override
	@Transactional
	public List<GiftCertificate> findByTagName(String tagName, String orderBy) {
		List<GiftCertificate> giftCertificateList = giftCertificateDao.findEntityByTagName(tagName, orderBy);
		return giftCertificateList;
	}
//	
	@Override
	@Transactional
	public List<GiftCertificate> findByPartName(String name, String orderBy) {
		List<GiftCertificate> giftCertificateList = giftCertificateDao.findEntityByPartName(name, orderBy);
		return giftCertificateList;
	}

	@Override
	@Transactional
	public List<GiftCertificate> findByPartDescription(String description, String orderBy) {
		List<GiftCertificate> giftCertificateList = 
				giftCertificateDao.findEntityByPartDescription(description, orderBy);
		return giftCertificateList;
	}

	private void containsName(String name) {
		if (giftCertificateDao.findEntityByName(name).isPresent()) {
			throw new ResourceException(NAME_EXIST.getErrorMessageKey(), name,
					GIFT_CERTIFICATE_INCORRECT_DATA.getErrorCode());
		}
	}

	private void updateFields(GiftCertificate giftCertificateOld, GiftCertificate giftCertificate) {
		if (giftCertificate.getName() == null) {
			giftCertificate.setName(giftCertificateOld.getName());
		}
		if (giftCertificate.getDescription() == null) {
			giftCertificate.setDescription(giftCertificateOld.getDescription());
		}
		if (giftCertificate.getPrice() == null) {
			giftCertificate.setPrice(giftCertificateOld.getPrice());
		}
		if (giftCertificate.getDuration() == 0) {
			giftCertificate.setDuration(giftCertificateOld.getDuration());
		}
		giftCertificate.setCreateDate(giftCertificateOld.getCreateDate());
		giftCertificate.setLastUpdateDate(ZonedDateTime.now());
		giftCertificate.setId(giftCertificateOld.getId());
	}

	private void updateGiftCertificateTag(GiftCertificate giftCertificateOld, GiftCertificate giftCertificate) {
		List<Tag> tagList = giftCertificate.getTags();
		if (tagList != null && !tagList.equals(giftCertificateOld.getTags())) {
			if (giftCertificateOld.getTags() != null) {
				giftCertificateDao.deleteGiftCertificateTag(giftCertificateOld.getId());
			}
			createGiftCertificateTag(giftCertificate);
		}
	}
}
