package com.epam.esm.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.PageDto;
import com.epam.esm.hateoas.GiftCertificateHateoasUtil;
import com.epam.esm.response.GiftCertificateResponse;
import com.epam.esm.response.PageGiftCertificateResponse;
import com.epam.esm.service.GiftCertificateService;

/**
 * The {@code GiftCertificateController} class contains endpoint of the API
 * 
 * @author Ihar Klepcha
 */
@Validated
@RestController
@RequestMapping("/gift-certificates")
public class GiftCertificateController {
	public static Logger log = LogManager.getLogger();
	private GiftCertificateService giftCertificateService;

	/**
	 * Constructs a gift certificate controller
	 * 
	 * @param giftCertificateService {@link GiftCertificateService} service for
	 *                               certificate
	 */
	@Autowired
	public GiftCertificateController(GiftCertificateService giftCertificateService) {
		super();
		this.giftCertificateService = giftCertificateService;
	}

	@GetMapping("/health-check")
	@ResponseStatus(HttpStatus.OK)
	public String healthCheck() {
		log.info("Controller is worcking");
		return "Health check is worcking!";
	}

	/**
	 * Creates new gift certificate, processes POST requests at /gift-certificates
	 *
	 * @param giftCertificateDto {@link GiftCertificateDto} gift certificate DTO
	 * @return {@link GiftCertificateResponse} response
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ADMIN')")
	public GiftCertificateResponse createGiftCertificate(@Valid @RequestBody GiftCertificateDto giftCertificateDto) {
		log.info("Creating GiftCertificate");
		GiftCertificateDto giftCertificateDtoCreated = giftCertificateService.create(giftCertificateDto);
		GiftCertificateResponse response = GiftCertificateResponse.valueOf(giftCertificateDtoCreated);
		GiftCertificateHateoasUtil.addLinks(response);
		return response;
	}

	/**
	 * Gets gift certificates by params, processes GET requests at
	 * /gift-certificates
	 *
	 * @param params {@link Map} of {@link String} and {@link String} data for
	 *               searching gift certificates
	 * @return {@link PageGiftCertificateResponse} founded gift certificates
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PageGiftCertificateResponse getAllGiftCertificates(@RequestParam Map<String, String> params) {
		log.info("Finding GiftCertificates with parameters");
		PageDto<GiftCertificateDto> pageDto = giftCertificateService.find(params);
		PageGiftCertificateResponse response = PageGiftCertificateResponse.valueOf(pageDto);
		response.getContent().forEach(GiftCertificateHateoasUtil::addLinks);
		GiftCertificateHateoasUtil.addLinkOnPagedResourceRetrieval(response, params);
		return response;
	}

	/**
	 * Gets gift certificate by id, processes GET requests at
	 * /gift-certificates/{id}
	 *
	 * @param id is the gift certificate id
	 * @return {@link GiftCertificateResponse} founded gift certificate
	 */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GiftCertificateResponse getGiftCertificateById(@Positive @PathVariable long id) {
		log.info("Finding GiftCertificate by id");
		GiftCertificateDto giftCertificateDto = giftCertificateService.findById(id);
		GiftCertificateResponse response = GiftCertificateResponse.valueOf(giftCertificateDto);
		GiftCertificateHateoasUtil.addLinks(response);
		return response;
	}

	/**
	 * Updates gift certificate, processes PUT requests at /gift-certificates/{id}
	 *
	 * @param id                 is the gift certificate id
	 * @param giftCertificateDto {@link GiftCertificateDto} data for updating gift
	 *                           certificate
	 * @return {@link GiftCertificateRespons} updated gift certificate
	 */
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public GiftCertificateResponse updateGiftCertificate(@Positive @PathVariable long id,
			@RequestBody GiftCertificateDto giftCertificateDto) {
		log.info("Updating GiftCertificate");
		giftCertificateDto.setId(id);
		GiftCertificateDto giftCertificateDtoUpdated = giftCertificateService.update(giftCertificateDto);
		GiftCertificateResponse response = GiftCertificateResponse.valueOf(giftCertificateDtoUpdated);
		GiftCertificateHateoasUtil.addLinks(response);
		return response;
	}

	/**
	 * Deletes gift certificate by id, processes DELETE requests at
	 * /gift-certificates/{id}
	 *
	 * @param id is the gift certificate id
	 * @return {@link ResponseEntity} response
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteGiftCertificate(@Positive @PathVariable long id) {
		log.info("Deleting GiftCertificate");
		giftCertificateService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
