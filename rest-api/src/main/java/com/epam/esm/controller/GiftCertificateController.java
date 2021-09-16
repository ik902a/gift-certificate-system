package com.epam.esm.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;


@RestController
@RequestMapping("/gift-certificates")
public class GiftCertificateController {
	public static Logger log = LogManager.getLogger();
	
	@Autowired
	private GiftCertificateService giftCertificateService;
	
	@GetMapping("/hello")
	@ResponseStatus(HttpStatus.OK)
	public String hello() {
		log.info("----------------------Hello----------------");
		return "Hello Spring!";
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public GiftCertificate createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
		GiftCertificate giftCertificateCreated = giftCertificateService.create(giftCertificate);
		return giftCertificateCreated;
	}

//	@GetMapping(produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public List<GiftCertificate> getAllGiftSertificates() {
//		log.info("Get mapping");
//		List<GiftCertificate> giftCertificates = giftCertificateService.findAll();
//		return giftCertificates;
//	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GiftCertificate getGiftCertificate(@PathVariable long id) {
		GiftCertificate giftCertificate = giftCertificateService.findById(id);
		return giftCertificate;
	}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificate updateGiftCertificate(@PathVariable long id, 
    		@RequestBody GiftCertificate giftCertificate) {
        giftCertificate.setId(id);
        return giftCertificateService.update(giftCertificate);
    }
	
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGiftCertificate(@PathVariable long id) {
        giftCertificateService.delete(id);
    }

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GiftCertificate> getGiftCertificates(@RequestParam Map<String, String> params) {

		log.info("Get mapping with Params {}", params.toString() );
		List<GiftCertificate> giftCertificates = giftCertificateService.findAll(params);

		return giftCertificates;

//      Command command;
//		try {
//			command = commandProvider.defineCommand(params.keySet());
//		} catch (Exception e) {//TODO
//			command = null;
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
    
    
    
    
//
//	
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<GiftCertificate> getGiftCertificates(@RequestParam Map<String, String> params) {
//        Command command;
//		try {
//			command = commandProvider.defineCommand(params.keySet());
//		} catch (Exception e) {//TODO
//			command = null;
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        return command.execute(params);
//    }
}
