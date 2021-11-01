package com.epam.esm.dto;

import java.util.Objects;

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
	 * Constructs a gift certificate order DTO
	 */
	public GiftCertificateOrderDto() {
		super();
	}

	/**
	 * Constructs a gift certificate order DTO with the specified
	 * 
	 * @param giftCertificate {@link GiftCertificatDto} gift certificate
	 * @param quantity is quantity of gift certificate
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
		return Objects.hash(giftCertificate, quantity);
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
		return Objects.equals(giftCertificate, other.giftCertificate) && quantity == other.quantity;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nGiftCertificateOrderDTO{ giftCertificate=").append(giftCertificate);
		sb.append(", quantity=").append(quantity).append(" }");
		return sb.toString();
	}
}
