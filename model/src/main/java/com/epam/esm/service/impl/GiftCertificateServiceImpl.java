package com.epam.esm.service.impl;

import static com.epam.esm.dao.util.ParamName.LIMIT;
import static com.epam.esm.dao.util.ParamName.OFFSET;
import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;

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
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotExistException;
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
		log.info("Creating GiftCertificate from {}", giftCertificateDto);
		GiftCertificate giftCertificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
		if (giftCertificate.getTags() != null) {
			giftCertificate.setTags(createNewTag(giftCertificate.getTags()));
		}
		giftCertificate = giftCertificateDao.create(giftCertificate);
		return modelMapper.map(giftCertificate, GiftCertificateDto.class);
	}
	
	private List<Tag> createNewTag(List<Tag> tags) {
		return tags.stream()
				.map(tag -> tagDao.findEntityByName(tag.getName()).orElseGet(() -> tagDao.create(tag)))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public PageDto<GiftCertificateDto> find(Map<String, String> params) {
		log.info("Finding GiftCertificate with parameters: {}", params);
		List<GiftCertificate> giftCertificateList = giftCertificateDao.find(params);
	    List<GiftCertificateDto> giftCertificateDtoList = giftCertificateList.stream()
                .map(giftCertificate -> modelMapper.map(giftCertificate, GiftCertificateDto.class))
                .collect(Collectors.toList());
	    return buildPage(giftCertificateDtoList, params);
	}
	
	private PageDto<GiftCertificateDto> buildPage(List<GiftCertificateDto> giftCertificateDtoList
			, Map<String, String> params) {
		int offset = Integer.parseInt(params.get(OFFSET));
		int limit = Integer.parseInt(params.get(LIMIT));
		long totalPositions = giftCertificateDao.getTotalNumber(params);
		long totalPages = (long) Math.ceil((double) totalPositions / limit);
		long pageNumber = offset / limit + 1;
      return new PageDto<>(giftCertificateDtoList, totalPages, pageNumber, offset, limit);
	}

	@Override
	@Transactional
	public GiftCertificateDto findById(long id) {
		log.info("Finding GiftCertificate by id={}", id);
		Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findEntityById(id);
		GiftCertificate giftCertificate = giftCertificateOptional.orElseThrow(
				() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
						, String.valueOf(id)
						, GIFT_CERTIFICATE_INCORRECT.getErrorCode()));
		return modelMapper.map(giftCertificate, GiftCertificateDto.class);
	}

	@Override
	@Transactional
	public GiftCertificateDto update(GiftCertificateDto giftCertificateNewDto) {
		log.info("Updating GiftCertificate by data: {}", giftCertificateNewDto);
		Optional<GiftCertificate> giftCertificateOptional = 
				giftCertificateDao.findEntityById(giftCertificateNewDto.getId());
		GiftCertificate giftCertificate = giftCertificateOptional.orElseThrow(
				() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
						, giftCertificateNewDto.getId().toString()
						, GIFT_CERTIFICATE_INCORRECT.getErrorCode()));
		GiftCertificate giftCertificateNew = modelMapper.map(giftCertificateNewDto, GiftCertificate.class);
		updateFields(giftCertificate, giftCertificateNew);
		return modelMapper.map(giftCertificate, GiftCertificateDto.class);
	}
	
	private void updateFields(GiftCertificate giftCertificate, GiftCertificate giftCertificateNew) {
		if (giftCertificateNew.getName() != null) {
			giftCertificate.setName(giftCertificateNew.getName());
		}
		if (giftCertificateNew.getDescription() != null) {
			giftCertificate.setDescription(giftCertificateNew.getDescription());
		}
		if (giftCertificateNew.getPrice() != null) {
			giftCertificate.setPrice(giftCertificateNew.getPrice());
		}
		if (giftCertificateNew.getDuration() != null) {
			giftCertificate.setDuration(giftCertificateNew.getDuration());
		}
        if(giftCertificateNew.getTags() != null){
            giftCertificate.setTags(createNewTag(giftCertificateNew.getTags()));
        }
	}
	
	@Override
	@Transactional
	public void delete(long id) {
		log.info("Deleting GiftCertificate by id={}", id);
		if (!giftCertificateDao.delete(id)) {
			throw new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
					, String.valueOf(id)
					, GIFT_CERTIFICATE_INCORRECT.getErrorCode());
		}
	}
}
