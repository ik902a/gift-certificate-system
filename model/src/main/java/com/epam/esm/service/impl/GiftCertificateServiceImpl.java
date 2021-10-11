package com.epam.esm.service.impl;

//import static com.epam.esm.exception.ErrorCode.*;
//import static com.epam.esm.exception.ErrorMessageKey.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
//import com.epam.esm.exception.InvalidDataException;
//import com.epam.esm.exception.ResourceNotExistException;
import com.epam.esm.service.GiftCertificateService;


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
	@Autowired
	private TagDao tagDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public GiftCertificateDto create(GiftCertificateDto giftCertificateDto) {
		log.info("CREATE GiftCertificate Service {}", giftCertificateDto);
		
//		GiftCertificateValidator.validateGiftCertificate(giftCertificate);
//		throwErrorIfCertificateExist(giftCertificate.getName());
		ZonedDateTime currentDate = ZonedDateTime.now();
		giftCertificateDto.setCreateDate(currentDate);
		giftCertificateDto.setLastUpdateDate(currentDate);
		GiftCertificate giftCertificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
		if (giftCertificate.getTags() != null) {
			createGiftCertificateTag(giftCertificate);
		}
		giftCertificate = giftCertificateDao.create(giftCertificate);
		return modelMapper.map(giftCertificate, GiftCertificateDto.class);
	}
	
	private void createGiftCertificateTag(GiftCertificate giftCertificate) {
		List<Tag> tags = giftCertificate.getTags();
		tags.forEach(
				tag -> tag.setId(tagDao.findEntityByName(tag.getName()).orElseGet(
						() -> tagDao.create(tag)).getId()));
//		giftCertificateDao.createGiftCertificateTag(giftCertificate);
	}

	@Override
	@Transactional
	public PageDto<GiftCertificateDto> find(Map<String, String> params) {
		log.info("FIND GiftCertificate BY PARAMS Service {}", params);
		List<GiftCertificate> giftCertificateList = giftCertificateDao.find(params);
	    List<GiftCertificateDto> giftCertificateDtoList = giftCertificateList.stream()
                .map(giftCertificate -> modelMapper.map(giftCertificate, GiftCertificateDto.class))
                .collect(Collectors.toList());
	    
	    long totalNumberPositions = giftCertificateDao.getTotalNumber(params);
        return new PageDto<>(giftCertificateDtoList, totalNumberPositions);
//		return addTags(giftCertificateList);//TODO Deprecate
	}
	

	
	@Override
	@Transactional
	public GiftCertificateDto findById(long id) {
		log.info("FIND GiftCertificate BY ID Service id={}", id);
		GiftCertificate giftCertificate = giftCertificateDao.findEntityById(id);
		return modelMapper.map(giftCertificate, GiftCertificateDto.class);
	}
//	@Override
//	@Transactional
//	public GiftCertificateDto findById(long id) {
////		GiftCertificateValidator.validateId(id);//TODO valid id
//		GiftCertificate giftCertificate = giftCertificateDao.findEntityById(id);
//		
////		GiftCertificate giftCertificate = giftCertificateOptional.orElseThrow(
////				() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey(),
////				String.valueOf(id), 
////				GIFT_CERTIFICATE_INCORRECT_ID.getErrorCode()));
//	
//		return modelMapper.map(giftCertificate, GiftCertificateDto.class);
////		return addTags(giftCertificate);
//	}

	@Override
	@Transactional
	public GiftCertificateDto update(GiftCertificateDto giftCertificateDto) {
		log.info("UPDATE GiftCertificate Service {}", giftCertificateDto);
		GiftCertificateDto giftCertificateOldDto = findById(giftCertificateDto.getId());
		giftCertificateDto = updateFields(giftCertificateOldDto, giftCertificateDto);
		GiftCertificate giftCertificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
		if (giftCertificate.getTags() != null) {
			createGiftCertificateTag(giftCertificate);
		}
		giftCertificate = giftCertificateDao.update(giftCertificate);
		return modelMapper.map(giftCertificate, GiftCertificateDto.class);

	}
//	@Override
//	@Transactional
//	public GiftCertificate update(GiftCertificate giftCertificate) {
//		GiftCertificate giftCertificateOld = findById(giftCertificate.getId());
////		String newName = giftCertificate.getName();
////		if (newName != null && !newName.equals(giftCertificateOld.getName())) {
////			throwErrorIfCertificateExist(newName);
////		}
//		giftCertificate = updateFields(giftCertificateOld, giftCertificate);
////		GiftCertificateValidator.validateGiftCertificate(giftCertificate);
//		updateGiftCertificateTag(giftCertificateOld, giftCertificate);
//		giftCertificate = giftCertificateDao.update(giftCertificate);
//		return giftCertificate;
//		return addTags(giftCertificate);
//	}

	@Override
	@Transactional
	public void delete(long id) {
		log.info("DELETE GiftCertificate Service id={}", id);
		giftCertificateDao.delete(id);
	}
//	@Override
//	@Transactional
//	public void delete(long id) {
//		//GiftCertificateValidator.validateId(id);//TODO valid id
//		if (!giftCertificateDao.delete(id)) {
////			throw new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey(), 
////					String.valueOf(id),
////					GIFT_CERTIFICATE_INCORRECT_ID.getErrorCode());
//		}
//	}
	
	private GiftCertificateDto updateFields(GiftCertificateDto giftCertificateOldDto
			, GiftCertificateDto giftCertificateDto) {
		if (giftCertificateDto.getName() == null) {
			giftCertificateDto.setName(giftCertificateOldDto.getName());
		}
		if (giftCertificateDto.getDescription() == null) {
			giftCertificateDto.setDescription(giftCertificateOldDto.getDescription());
		}
		if (giftCertificateDto.getPrice() == null) {
			giftCertificateDto.setPrice(giftCertificateOldDto.getPrice());
		}
		if (giftCertificateDto.getDuration() == 0) {
			giftCertificateDto.setDuration(giftCertificateOldDto.getDuration());
		}
        if(giftCertificateDto.getTags() == null){
            giftCertificateDto.setTags(giftCertificateOldDto.getTags());
        }
		giftCertificateDto.setCreateDate(giftCertificateOldDto.getCreateDate());
		giftCertificateDto.setLastUpdateDate(ZonedDateTime.now());
		giftCertificateDto.setId(giftCertificateOldDto.getId());
		return giftCertificateDto;
	}

//	private void updateGiftCertificateTag(GiftCertificate giftCertificateOld, 
//			GiftCertificate giftCertificate) {
//		List<Tag> tagList = giftCertificateDto.getTags();
//		if (tagList != null && !tagList.equals(giftCertificateOld.getTags())) {
//			if (giftCertificateOld.getTags() != null) {
//				giftCertificateDao.deleteGiftCertificateTag(giftCertificateOld.getId());
//			}
//			createGiftCertificateTag(giftCertificate);
//		}
//	}
	
//	private void throwErrorIfCertificateExist(String name) {
//	if (giftCertificateDao.findEntityByName(name).isPresent()) {
//		throw new InvalidDataException(List.of(NAME_EXIST.getErrorMessageKey()),
//				name,
//				GIFT_CERTIFICATE_INCORRECT_DATA.getErrorCode());
//	}
//}
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
