package com.epam.esm.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.epam.esm.entity.audit.OrderAudit;

/**
 * The {@code Order} class describes the entity order
 * 
 * @author Ihar Klepcha
 * @see AbstractEntity
 */
@Entity
@Table(name="orders")
@EntityListeners(OrderAudit.class)
public class Order extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="date")
	private ZonedDateTime date;
	@Column(name="cost")
	private BigDecimal cost;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL)    
	private List<GiftCertificateOrder> giftCertificateOrderList;
	
	/**
	 * Constructs a new order
	 */
	public Order() {
		super();
	}

	/**
	 * Constructs a new order with the specified
	 * 
	 * @param id {@link Long} order id
	 * @param date {@link ZonedDateTime} creating date
	 * @param cost {@link BigDecimal} cost order
	 * @param user {@link UserInOrder} is user owner order
	 * @param giftCertificateOrderList {@link List} of {@link GiftCertificateOrder} is list 
	 * gift certificates
	 */
	public Order(Long id, ZonedDateTime date, BigDecimal cost, User user,
			List<GiftCertificateOrder> giftCertificateOrderList) {
		super();
		this.id = id;
		this.date = date;
		this.cost = cost;
		this.user = user;
		this.giftCertificateOrderList = giftCertificateOrderList;
	}

	public void addGiftCertificateOrder(GiftCertificateOrder giftCertificateOrder) {
		if (giftCertificateOrderList == null) {
			giftCertificateOrderList = new ArrayList<>();
		}
		giftCertificateOrderList.add(giftCertificateOrder);
		giftCertificateOrder.setOrder(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<GiftCertificateOrder> getGiftCertificateOrderList() {
		return giftCertificateOrderList;
	}

	public void setGiftCertificateOrderList(List<GiftCertificateOrder> giftCertificateOrderList) {
		this.giftCertificateOrderList = giftCertificateOrderList;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nOrder{ id=").append(id);
		sb.append(", date=").append(date);
		sb.append(", cost=").append(cost).append(" }");
		return sb.toString();
	}
}
