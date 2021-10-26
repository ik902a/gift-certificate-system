package com.epam.esm.dto;

import java.util.Map;

import javax.validation.constraints.Positive;

/**
 * The {@code OrderDataDto} class has information for making order
 *
 * @author Ihar Klepcha
 */
public class OrderDataDto {// TODO Depricate
	Map<@Positive Long, @Positive Integer> giftCertificateMap;
	
    /**
	 * Constructs a new Order data DTO
	 */
	public OrderDataDto() {
		super();
	}

	/**
	 * Constructs a new order data DTO with the specified
	 * 
	 * @param userId is user id
	 * @param giftCertificateMap {@link Map} of {@link Map} and {@link Map} data for creating order
	 */
	public OrderDataDto(long userId, Map<Long, Integer> giftCertificateMap) {
		super();
		this.giftCertificateMap = giftCertificateMap;
	}


	public Map<Long, Integer> getGiftCertificateMap() {
		return giftCertificateMap;
	}

	public void setGiftCertificateMap(Map<Long, Integer> giftCertificateMap) {
		this.giftCertificateMap = giftCertificateMap;
	}



	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nOrder Data DTO{ map=").append(giftCertificateMap).append(" }");
		return sb.toString();
	}
}
