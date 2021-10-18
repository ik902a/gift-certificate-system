package com.epam.esm.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
 * @see Entity
 */
@Entity
@Table(name="orders")
@EntityListeners(OrderAudit.class)
public class Order extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="date")
	private ZonedDateTime date;
	@Column(name="cost")
	private BigDecimal cost;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy="order")    
	private List<GiftCertificateOrder> giftCertificateOrderList;
	
	public Order() {
		super();
	}

	public Order(long id, ZonedDateTime date, BigDecimal cost, User user,
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

//	@Override
//	public String toString() {
//		return "Order [id=" + id + ", date=" + date + ", cost=" + cost + ", user=" + user
//				+ ", giftCertificateOrderList=" + giftCertificateOrderList + "]";
//	}
}
