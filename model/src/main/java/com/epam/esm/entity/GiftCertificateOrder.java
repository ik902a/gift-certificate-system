package com.epam.esm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * The {@code GiftCertificateOrder} class describes the connection gift certificate and order
 * 
 * @author Ihar Klepcha
 * @see Entity
 */
@Entity
@Table(name="gift_certificates_orders")
public class GiftCertificateOrder extends AbstractEntity {
	private static final long serialVersionUID = 1L;
    @EmbeddedId
    GiftCertificateOrderKey id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    Order order;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("giftCertificateId")
    @JoinColumn(name = "gift_certificate_id")
    GiftCertificate giftCertificate;
    
    @Column(name = "quantity")
    int quantity;

	public GiftCertificateOrder() {
		super();
	}

	public GiftCertificateOrder(GiftCertificateOrderKey id, Order order, GiftCertificate giftCertificate,
			int quantity) {
		super();
		this.id = id;
		this.order = order;
		this.giftCertificate = giftCertificate;
		this.quantity = quantity;
	}
	
	public GiftCertificateOrder(Order order, GiftCertificate giftCertificate, int quantity) {
		super();
		this.order = order;
		this.giftCertificate = giftCertificate;
		this.quantity = quantity;
	}

	public GiftCertificateOrderKey getId() {
		return id;
	}

	public void setId(GiftCertificateOrderKey id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public GiftCertificate getGiftCertificate() {
		return giftCertificate;
	}

	public void setGiftCertificate(GiftCertificate giftCertificate) {
		this.giftCertificate = giftCertificate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
