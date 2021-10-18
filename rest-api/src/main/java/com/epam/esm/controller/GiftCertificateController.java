package com.epam.esm.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.epam.esm.hateoas.GiftCertificateHateoas;
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
	@Autowired
	private GiftCertificateService giftCertificateService;
	
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
     * @return {@link GiftCertificateDto} created gift certificate DTO
     */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GiftCertificateDto createGiftCertificate(@Valid @RequestBody GiftCertificateDto giftCertificateDto) {
		GiftCertificateDto giftCertificateDtoCreated = giftCertificateService.create(giftCertificateDto);
		log.info("CREATE GiftCertificate DTO Controller");
		GiftCertificateHateoas.addLinks(giftCertificateDtoCreated);
		return giftCertificateDtoCreated;
	}

	/**
     * Gets gift certificates by params, processes GET requests at /gift-certificates
     *
     * @param params {@link Map} of {@link String} and {@link String} data for searching gift certificates
     * @return pageDto {@link PageDto} of {@link GiftCertificateDto} founded gift certificates
     */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PageDto<GiftCertificateDto> getAllGiftCertificates(@Valid @RequestParam Map<String, String> params) {
		PageDto<GiftCertificateDto> pageDto = giftCertificateService.find(params);
		pageDto.getContent().forEach(GiftCertificateHateoas::addLinks);
		GiftCertificateHateoas.addLinkOnPagedResourceRetrieval(pageDto, params);
		log.info("FIND all Gift Certificate DTO Controller");
		return  pageDto;
	}

	/**
     * Gets gift certificate by id, processes GET requests at /gift-certificates/{id}
     *
     * @param id is the gift certificate id
     * @return founded gift certificate
     */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GiftCertificateDto getGiftCertificateById(@Positive @PathVariable long id) {
		GiftCertificateDto giftCertificateDto = giftCertificateService.findById(id);
		GiftCertificateHateoas.addLinks(giftCertificateDto);
		log.info("FIND Gift Certificate DTO by id Controller");
		return giftCertificateDto;
	}

	 /**
     * Updates gift certificate, processes PUT requests at /gift-certificates/{id}
     *
     * @param id is the gift certificate id
     * @param giftCertificate {@link GiftCertificateDto} data for updating gift certificate
     * @return {@link GiftCertificateDto} updated gift certificate
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto updateGiftCertificate(@Positive @PathVariable long id, 
    		@RequestBody GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.setId(id);
        GiftCertificateDto giftCertificateDtoUpdated = giftCertificateService.update(giftCertificateDto);
        GiftCertificateHateoas.addLinks(giftCertificateDtoUpdated);
		log.info("UPDATE Gift Certificate DTO Controller");
        return giftCertificateDtoUpdated;
    }
	
    /**
     * Deletes gift certificate by id, processes DELETE requests at /gift-certificates/{id}
     *
     * @param id is the gift certificate id 
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteGiftCertificate(@Positive @PathVariable long id) {
        giftCertificateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
