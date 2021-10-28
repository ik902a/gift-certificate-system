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
	private Long orderId;
	@Column(name = "gift_certificate_id")
	private Long giftCertificateId;

	/**
	 * Constructs a new gift certificate order key
	 */
	public GiftCertificateOrderKey() {
		super();
	}

	/**
	 * Constructs a new gift certificate order key with the specified
	 * 
	 * @param orderId {@link Long} order id
	 * @param giftCertificateId {@link Long} gift certificate id
	 */
	public GiftCertificateOrderKey(Long orderId, Long giftCertificateId) {
		super();
		this.orderId = orderId;
		this.giftCertificateId = giftCertificateId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getGiftCertificateId() {
		return giftCertificateId;
	}

	public void setGiftCertificateId(Long giftCertificateId) {
		this.giftCertificateId = giftCertificateId;
	}
}
