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
 * @see AbstractEntity
 */
@Entity
@Table(name="gift_certificates_orders")
public class GiftCertificateOrder extends AbstractEntity {
	private static final long serialVersionUID = 1L;
    @EmbeddedId
    private GiftCertificateOrderKey id;
    @ManyToOne(cascade=CascadeType.ALL)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(cascade=CascadeType.ALL)
    @MapsId("giftCertificateId")
    @JoinColumn(name = "gift_certificate_id")
    private GiftCertificate giftCertificate;
    @Column(name = "quantity")
    private int quantity;

	/**
	 * Constructs a gift certificate order
	 */
	public GiftCertificateOrder() {
		super();
	}
	
	/**
	 * Constructs a gift certificate order with the specified
	 * 
	 * @param order {@link Order} order
	 * @param giftCertificate {@link GiftCertificete} gift certificate
	 * @param quantity is quantity orders
	 */
	public GiftCertificateOrder(Order order, GiftCertificate giftCertificate, int quantity) {
		super();
		this.id = new GiftCertificateOrderKey(order.getId(), giftCertificate.getId());
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
