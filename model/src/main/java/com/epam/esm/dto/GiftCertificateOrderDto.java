package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The {@code GiftCertificateOrderDto} class is implementation of pattern DTO for transmission 
 * gift certificate order entity between service and controller
 * 
 * @author Ihar Klepcha
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GiftCertificateOrderDto {
    GiftCertificateDto giftCertificate;
    int quantity;
    
    /**
	 * Constructs a new Gift Certificate Order DTO
	 */
	public GiftCertificateOrderDto() {
		super();
	}

	/**
	 * Constructs a new gift certificate order DTO with the specified
	 * 
	 * @param giftCertificate {@link GiftCertificatDto} gift certificate
	 * @param quantity of gift certificate
	 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((giftCertificate == null) ? 0 : giftCertificate.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiftCertificateOrderDto other = (GiftCertificateOrderDto) obj;
		if (giftCertificate == null) {
			if (other.giftCertificate != null)
				return false;
		} else if (!giftCertificate.equals(other.giftCertificate))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
    
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nGiftCertificateOrderDTO{ giftCertificate=").append(giftCertificate);
		sb.append(", quantity=").append(quantity).append(" }");
		return sb.toString();
	}
}
