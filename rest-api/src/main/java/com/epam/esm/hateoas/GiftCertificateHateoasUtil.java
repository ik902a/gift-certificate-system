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
import com.epam.esm.response.GiftCertificateResponse;
import com.epam.esm.response.PageGiftCertificateResponse;

/**
 * The {@code GiftCertificateHateoas} class makes HATEOAS for gift сertificates
 * 
 * @author Ihar Klepcha
 */
public class GiftCertificateHateoasUtil {
	public static Logger log = LogManager.getLogger();
    
	/**
	 * Adds HATEOAS links
	 * 
	 * @param giftCertificate {@link GiftCertificateResponse} gift certificate
	 */
    public static void addLinks (GiftCertificateResponse giftCertificate){
        giftCertificate.add(linkTo(methodOn(GiftCertificateController.class)
        		.getGiftCertificateById(giftCertificate.getId())).withSelfRel());
        giftCertificate.add(linkTo(methodOn(GiftCertificateController.class)
        		.deleteGiftCertificate(giftCertificate.getId())).withRel(DELETE));
    }  

	/**
	 * Adds HATEOAS links to page
	 * 
	 * @param page {@link PageDto} of {@link GiftCertificateDto} page
	 * @param params {@link Map} of {@link String} and {@link String} parameters
	 */
    public static void addLinkOnPagedResourceRetrieval(PageGiftCertificateResponse page, Map<String, String> params) {
    	int offset = Integer.parseInt(params.get(OFFSET));
		int limit = Integer.parseInt(params.get(LIMIT));
    	if (hasNextPage(page.getPageNumber(), page.getTotalPages())) {
			params.put(OFFSET, String.valueOf(offset + limit));
			page.add(linkTo(methodOn(GiftCertificateController.class)
					.getAllGiftCertificates(params)).withRel(NEXT));
		}
		if (hasPreviousPage(page.getPageNumber())) {
			params.put(OFFSET, String.valueOf(offset - limit));
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
