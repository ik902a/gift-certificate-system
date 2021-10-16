package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GiftCertificateOrderDto {
    GiftCertificateDto giftCertificate;
    int quantity;
    
	public GiftCertificateOrderDto() {
		super();
	}

	public GiftCertificateOrderDto(GiftCertificateDto giftCertificate, int quantity) {
		super();
		this.giftCertificate = giftCertificate;
		this.quantity = quantity;
	}

	public GiftCertificateDto getGiftCertificate() {
		return giftCertificate;
	}

	public void setGiftCertificate(GiftCertificateDto giftCertificate) {
		this.giftCertificate = giftCertificate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    

}
