package com.epam.esm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
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
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.entity.Tag;

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
		log.info("Controller CREATE GiftCertificate is worcking");
		return giftCertificateDtoCreated;
	}
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public GiftCertificateDto createGiftCertificate(
//			@Valid @RequestBody GiftCertificateDto giftCertificateDto, Errors errors) {
//		if (errors.hasErrors()) {
//			return giftCertificateDto;
//			}
//		GiftCertificateDto giftCertificateDtoCreated = giftCertificateService.create(giftCertificateDto);
//		log.info("Controller CREATE GiftCertificate is worcking");
//		return giftCertificateDtoCreated;
//	}



	/**
     * Gets gift certificates by params, processes GET requests at /gift-certificates
     *
     * @param params {@link Map} of {@link String} and {@link String} data for searching gift certificates
     * @return {@link List} of {@link GiftCertificate} founded gift certificates
     */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GiftCertificateDto> getGiftCertificates(@RequestParam Map<String, String> params) {
		List<GiftCertificateDto> giftCertificatesDto = giftCertificateService.find(params);
		log.info("GET mapping. Controller is worcking");
		log.info("FIND GiftCertificate DTO Controller {}", giftCertificatesDto.get(0).toString());
		return giftCertificatesDto;
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
		return giftCertificateDto;
	}

//	 /**
//     * Updates gift certificate, processes PUT requests at /gift-certificates/{id}
//     *
//     * @param id is the gift certificate id
//     * @param giftCertificate {@link GiftCertificate} data for updating gift certificate
//     * @return {@link GiftCertificate} updated gift certificate
//     */
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public GiftCertificate updateGiftCertificate(@PathVariable long id, 
//    		@RequestBody GiftCertificate giftCertificate) {
//        giftCertificate.setId(id);
//        return giftCertificateService.update(giftCertificate);
//    }
	
    /**
     * Deletes gift certificate by id, processes DELETE requests at /gift-certificates/{id}
     *
     * @param id is the gift certificate id 
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGiftCertificate(@PathVariable long id) {
        giftCertificateService.delete(id);
    }
}
