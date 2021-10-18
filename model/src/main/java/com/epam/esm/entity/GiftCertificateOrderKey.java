package com.epam.esm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The {@code GiftCertificateOrderKey} class is embedded id
 * 
 * @author Ihar Klepcha
 * @see Serializable
 */
@Embeddable
public class GiftCertificateOrderKey implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "order_id")
	long orderId;
	@Column(name = "gift_certificate_id")
	long giftCertificateId;

	/**
	 * Constructs a new gift certificate order key
	 */
	public GiftCertificateOrderKey() {
		super();
	}

	/**
	 * Constructs a new gift certificate order key with the specified
	 * 
	 * @param orderId is order id
	 * @param giftCertificateId is gift certificate id
	 */
	public GiftCertificateOrderKey(long orderId, long giftCertificateId) {
		super();
		this.orderId = orderId;
		this.giftCertificateId = giftCertificateId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getGiftCertificateId() {
		return giftCertificateId;
	}

	public void setGiftCertificateId(long giftCertificateId) {
		this.giftCertificateId = giftCertificateId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (giftCertificateId ^ (giftCertificateId >>> 32));
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
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
		GiftCertificateOrderKey other = (GiftCertificateOrderKey) obj;
		if (giftCertificateId != other.giftCertificateId)
			return false;
		if (orderId != other.orderId)
			return false;
		return true;
	}
}
