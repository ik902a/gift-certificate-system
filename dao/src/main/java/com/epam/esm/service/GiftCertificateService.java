package com.epam.esm.service;

import java.util.List;
import java.util.Map;

import com.epam.esm.entity.GiftCertificate;

public interface GiftCertificateService {
	
	GiftCertificate create(GiftCertificate giftCertificate);
	
//	List<GiftCertificate> findAll();
	
	List<GiftCertificate> findAll(Map<String, String> params);
	   
	GiftCertificate findById(long id);
    
    GiftCertificate update(GiftCertificate giftCertificate);
    
    void delete(long id);
    
	List<GiftCertificate> findByTagName(String tagName, String orderBy);
	
	List<GiftCertificate> findByPartName(String name, String orderBy);
	
	List<GiftCertificate> findByPartDescription(String description, String orderBy);

}
