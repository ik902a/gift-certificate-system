package com.epam.esm.dto;

import java.util.Map;

import javax.validation.constraints.Positive;

/**
 * The {@code OrderDataDto} class has information for making order
 *
 * @author Ihar Klepcha
 */
public class OrderDataDto {
	@Positive
	long userId;
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
		this.userId = userId;
		this.giftCertificateMap = giftCertificateMap;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Map<Long, Integer> getGiftCertificateMap() {
		return giftCertificateMap;
	}

	public void setGiftCertificateMap(Map<Long, Integer> giftCertificateMap) {
		this.giftCertificateMap = giftCertificateMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((giftCertificateMap == null) ? 0 : giftCertificateMap.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		OrderDataDto other = (OrderDataDto) obj;
		if (giftCertificateMap == null) {
			if (other.giftCertificateMap != null)
				return false;
		} else if (!giftCertificateMap.equals(other.giftCertificateMap))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nOrder Data DTO{ userId=").append(userId);
		sb.append(", map=").append(giftCertificateMap).append(" }");
		return sb.toString();
	}
}
