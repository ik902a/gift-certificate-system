package com.epam.esm.response;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.GiftCertificateOrderDto;

public class GiftCertificateOrderResponse extends RepresentationModel<GiftCertificateOrderResponse> {
	GiftCertificateResponse giftCertificate;
    int quantity;
    
	public GiftCertificateOrderResponse(GiftCertificateResponse giftCertificate, int quantity) {
		super();
		this.giftCertificate = giftCertificate;
		this.quantity = quantity;
	}

    public static GiftCertificateOrderResponse valueOf(GiftCertificateOrderDto giftCertificateOrderDto) {
    	return new GiftCertificateOrderResponse(
    			GiftCertificateResponse.valueOf(giftCertificateOrderDto.getGiftCertificate())
    			, giftCertificateOrderDto.getQuantity());
	}

	public GiftCertificateResponse getGiftCertificate() {
		return giftCertificate;
	}

	public void setGiftCertificate(GiftCertificateResponse giftCertificate) {
		this.giftCertificate = giftCertificate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
}
