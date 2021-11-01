package com.epam.esm.response;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.GiftCertificateOrderDto;

/**
 * The {@code GiftCertificateOrderResponse} class describes the response GiftCertificateOrder entity
 * 
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class GiftCertificateOrderResponse extends RepresentationModel<GiftCertificateOrderResponse> {
	GiftCertificateResponse giftCertificate;
    int quantity;
    
    /**
	 * Constructs a new response
	 */
	public GiftCertificateOrderResponse() {
		super();
	}

	/**
	 * Constructs a new response with the specified
	 * 
	 * @param giftCertificate {@link GiftCertificeteResponse} response
	 * @param quantity is quantity orders
	 */
	public GiftCertificateOrderResponse(GiftCertificateResponse giftCertificate, int quantity) {
		super();
		this.giftCertificate = giftCertificate;
		this.quantity = quantity;
	}

	/**
	 * Builds a new response 
	 * 
	 * @param giftCertificateOrderDto {@link GiftCertificateOrderDto}  entity
	 * @return {@link GiftCertificateOrderResponse} response
	 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(giftCertificate, quantity);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiftCertificateOrderResponse other = (GiftCertificateOrderResponse) obj;
		return Objects.equals(giftCertificate, other.giftCertificate) && quantity == other.quantity;
	}
}
