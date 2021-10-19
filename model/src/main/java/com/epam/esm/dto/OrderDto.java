package com.epam.esm.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The {@code OrderDto} class is implementation of pattern DTO for transmission order
 * entity between service and controller
 *
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class OrderDto {
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime date;
	private BigDecimal cost;
	private UserInOrderDto user;
	private List<GiftCertificateOrderDto> giftCertificateOrderList;
	
    /**
	 * Constructs a new order DTO
	 */
	public OrderDto() {
		super();
	}

	/**
	 * Constructs a new order DTO with the specified
	 * 
	 * @param id is order id
	 * @param date {@link ZonedDateTime} creating date
	 * @param cost {@link BigDecimal} cost order
	 * @param user {@link UserInOrderDto} is user owner order
	 * @param giftCertificateOrderList {@link List} of {@link GiftCertificateOrderDto} is list 
	 * gift certificates
	 */
	public OrderDto(long id, ZonedDateTime date, BigDecimal cost, UserInOrderDto user,
			List<GiftCertificateOrderDto> giftCertificateOrderList) {
		super();
		this.id = id;
		this.date = date;
		this.cost = cost;
		this.user = user;
		this.giftCertificateOrderList = giftCertificateOrderList;
	}

	public void addGiftCertificate(GiftCertificateOrderDto giftCertificate) {
        if (giftCertificateOrderList == null) {
        	giftCertificateOrderList = new ArrayList<>();
        }
        giftCertificateOrderList.add(giftCertificate);
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

	public UserInOrderDto getUser() {
		return user;
	}

	public void setUser(UserInOrderDto user) {
		this.user = user;
	}

	public List<GiftCertificateOrderDto> getGiftCertificateOrderList() {
		return giftCertificateOrderList;
	}

	public void setGiftCertificateOrderList(List<GiftCertificateOrderDto> giftCertificateOrderList) {
		this.giftCertificateOrderList = giftCertificateOrderList;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((giftCertificateOrderList == null) ? 0 : giftCertificateOrderList.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDto other = (OrderDto) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (giftCertificateOrderList == null) {
			if (other.giftCertificateOrderList != null)
				return false;
		} else if (!giftCertificateOrderList.equals(other.giftCertificateOrderList))
			return false;
		if (id != other.id)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nOrderDTO{ id=").append(id);
		sb.append(", date=").append(date);
		sb.append(", cost=").append(cost).append(" }");
		return sb.toString();
	}
}
