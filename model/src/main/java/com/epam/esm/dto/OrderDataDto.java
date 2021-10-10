package com.epam.esm.dto;

import java.util.Map;

import javax.validation.constraints.Positive;

import org.springframework.stereotype.Component;

@Component
public class OrderDataDto {
	@Positive
	long userId;
	Map<@Positive Long, @Positive Integer> giftCertificateMap;
	
	public OrderDataDto() {
		super();
	}

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
		return "OrderDto [userId=" + userId + ", giftCertificateMap=" + giftCertificateMap + "]";
	}
}
