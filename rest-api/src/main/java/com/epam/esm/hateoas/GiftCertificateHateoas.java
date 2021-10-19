package com.epam.esm.hateoas;

import static com.epam.esm.hateoas.LinkName.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.PageDto;

/**
 * The {@code GiftCertificateHateoas} class makes HATEOAS for gift сertificates
 * 
 * @author Ihar Klepcha
 */
public class GiftCertificateHateoas {
	public static Logger log = LogManager.getLogger();
    
	/**
	 * Adds HATEOAS links
	 * 
	 * @param giftCertificateDto {@link GiftCertificateDto} gift certificate
	 */
    public static void addLinks (GiftCertificateDto giftCertificateDto){
        giftCertificateDto.add(linkTo(methodOn(GiftCertificateController.class)
        		.getGiftCertificateById(giftCertificateDto.getId())).withSelfRel());
        giftCertificateDto.add(linkTo(methodOn(GiftCertificateController.class)
        		.updateGiftCertificate(giftCertificateDto.getId(), giftCertificateDto)).withRel(UPDATE));
        giftCertificateDto.add(linkTo(methodOn(GiftCertificateController.class)
        		.deleteGiftCertificate(giftCertificateDto.getId())).withRel(DELETE));
    }  

	/**
	 * Adds HATEOAS links to page
	 * 
	 * @param page {@link PageDto} of {@link GiftCertificateDto} page
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 */
    public static void addLinkOnPagedResourceRetrieval(PageDto<GiftCertificateDto> page, Map<String, String> params) {
		if (hasNextPage(page.getPageNumber(), page.getTotalPages())) {
			params.put(OFFSET, String.valueOf(page.getOffset() + page.getLimit()));
			page.add(linkTo(methodOn(GiftCertificateController.class)
					.getAllGiftCertificates(params)).withRel(NEXT));
		}
		if (hasPreviousPage(page.getPageNumber())) {
			params.put(OFFSET, String.valueOf(page.getOffset() - page.getLimit()));
			page.add(linkTo(methodOn(GiftCertificateController.class)
					.getAllGiftCertificates(params)).withRel(PREV));
		}
	}

	private static boolean hasNextPage(long page, long totalPages) {
		log.info("hasNextPage page={}, total={}", page, totalPages);
		return page < totalPages;
	}

	private static boolean hasPreviousPage(long page) {
		log.info("hasPreviousPage page={}", page);
		return page > 1;
	}
}
