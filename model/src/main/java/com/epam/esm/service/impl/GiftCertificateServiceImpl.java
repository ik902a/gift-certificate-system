package com.epam.esm.service.impl;

//import static com.epam.esm.exception.ErrorCode.*;
//import static com.epam.esm.exception.ErrorMessageKey.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
//import com.epam.esm.exception.InvalidDataException;
//import com.epam.esm.exception.ResourceNotExistException;
import com.epam.esm.service.GiftCertificateService;
//import com.epam.esm.validator.GiftCertificateValidator;

/**
 * The {@code GiftCertificateServiceImpl} class is responsible for operations with gift certificate
 * 
 * @author Ihar Klepcha
 * @see GiftCertificateService
 */
@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
	public static Logger log = LogManager.getLogger();
	@Autowired
	private GiftCertificateDao giftCertificateDao;
//	@Autowired
//	private TagDao tagDao;

//	@Override
//	@Transactional
//	public GiftCertificate create(GiftCertificate giftCertificate) {
//		log.info("CREATE {}", giftCertificate);
//		GiftCertificateValidator.validateGiftCertificate(giftCertificate);
//		throwErrorIfCertificateExist(giftCertificate.getName());
//		ZonedDateTime currentDate = ZonedDateTime.now();
//		giftCertificate.setCreateDate(currentDate);
//		giftCertificate.setLastUpdateDate(currentDate);
//		giftCertificate = giftCertificateDao.create(giftCertificate);
//		if (giftCertificate.getTags() != null) {
//			createGiftCertificateTag(giftCertificate);
//		}
//		return giftCertificate;
//	}
//
//	private void createGiftCertificateTag(GiftCertificate giftCertificate) {
//		List<Tag> tags = giftCertificate.getTags();
//		tags.forEach(
//				tag -> tag.setId(tagDao.findEntityByName(tag.getName()).orElseGet(
//						() -> tagDao.create(tag)).getId()));
//		giftCertificateDao.createGiftCertificateTag(giftCertificate);
//	}

	@Override
	@Transactional
	public List<GiftCertificate> find(Map<String, String> params) {
		List<GiftCertificate> giftCertificateList = giftCertificateDao.find(params);
		return giftCertificateList;
//		return addTags(giftCertificateList);
	}

//	@Override
//	@Transactional
//	public GiftCertificate findById(long id) {
//		GiftCertificateValidator.validateId(id);
//		Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findEntityById(id);
//		GiftCertificate giftCertificate;
//		if (giftCertificateOptional.isPresent()) {
//			giftCertificate = giftCertificateOptional.get();
//		} else {
//			throw new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey(), 
//					String.valueOf(id),
//					GIFT_CERTIFICATE_INCORRECT_ID.getErrorCode());
//		}
//		
//		return addTags(giftCertificate);
//	}
//
//	@Override
//	@Transactional
//	public GiftCertificate update(GiftCertificate giftCertificate) {
//		GiftCertificate giftCertificateOld = findById(giftCertificate.getId());
//		String newName = giftCertificate.getName();
//		if (newName != null && !newName.equals(giftCertificateOld.getName())) {
//			throwErrorIfCertificateExist(newName);
//		}
//		giftCertificate = updateFields(giftCertificateOld, giftCertificate);
//		GiftCertificateValidator.validateGiftCertificate(giftCertificate);
//		updateGiftCertificateTag(giftCertificateOld, giftCertificate);
//		giftCertificate = giftCertificateDao.update(giftCertificate);
//		return addTags(giftCertificate);
//	}
//
//	@Override
//	@Transactional
//	public void delete(long id) {
//		GiftCertificateValidator.validateId(id);
//		if (!giftCertificateDao.delete(id)) {
//			throw new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey(), 
//					String.valueOf(id),
//					GIFT_CERTIFICATE_INCORRECT_ID.getErrorCode());
//		}
//	}
//
//	private void throwErrorIfCertificateExist(String name) {
//		if (giftCertificateDao.findEntityByName(name).isPresent()) {
//			throw new InvalidDataException(List.of(NAME_EXIST.getErrorMessageKey()),
//					name,
//					GIFT_CERTIFICATE_INCORRECT_DATA.getErrorCode());
//		}
//	}
//
//	private GiftCertificate updateFields(GiftCertificate giftCertificateOld, GiftCertificate giftCertificate) {
//		if (giftCertificate.getName() == null) {
//			giftCertificate.setName(giftCertificateOld.getName());
//		}
//		if (giftCertificate.getDescription() == null) {
//			giftCertificate.setDescription(giftCertificateOld.getDescription());
//		}
//		if (giftCertificate.getPrice() == null) {
//			giftCertificate.setPrice(giftCertificateOld.getPrice());
//		}
//		if (giftCertificate.getDuration() == 0) {
//			giftCertificate.setDuration(giftCertificateOld.getDuration());
//		}
//		giftCertificate.setCreateDate(giftCertificateOld.getCreateDate());
//		giftCertificate.setLastUpdateDate(ZonedDateTime.now());
//		giftCertificate.setId(giftCertificateOld.getId());
//		return giftCertificate;
//	}
//
//	private void updateGiftCertificateTag(GiftCertificate giftCertificateOld, 
//			GiftCertificate giftCertificate) {
//		List<Tag> tagList = giftCertificate.getTags();
//		if (tagList != null && !tagList.equals(giftCertificateOld.getTags())) {
//			if (giftCertificateOld.getTags() != null) {
//				giftCertificateDao.deleteGiftCertificateTag(giftCertificateOld.getId());
//			}
//			createGiftCertificateTag(giftCertificate);
//		}
//	}
//	
//	private List<GiftCertificate> addTags(List<GiftCertificate> giftCertificateList) {
//		giftCertificateList.forEach(gc -> gc.setTags(tagDao.findEntityByGiftCertificate(gc.getId())));
//		return giftCertificateList;
//	}
//	
//	private GiftCertificate addTags(GiftCertificate giftCertificate) {
//		List<Tag> tagList = tagDao.findEntityByGiftCertificate(giftCertificate.getId());
//		giftCertificate.setTags(tagList);
//		return giftCertificate;
//	}
}
