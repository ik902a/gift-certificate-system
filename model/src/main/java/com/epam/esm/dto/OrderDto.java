package com.epam.esm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The {@code OrderDto} class is implementation of pattern DTO for transmission
 * order entity between service and controller
 *
 * @author Ihar Klepcha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
	private Long id;
	private LocalDateTime date;
	private BigDecimal cost;
	private List<GiftCertificateOrderDto> giftCertificateOrderList;

	/**
	 * Constructs a order DTO
	 */
	public OrderDto() {
		super();
	}

	/**
	 * Constructs a order DTO with the specified
	 * 
	 * @param id                       {@link Long} order id
	 * @param date                     {@link LocalDateTime} creating date
	 * @param cost                     {@link BigDecimal} cost order
	 * @param user                     {@link UserDto} is user owner order
	 * @param giftCertificateOrderList {@link List} of
	 *                                 {@link GiftCertificateOrderDto} is list gift
	 *                                 certificates
	 */
	public OrderDto(Long id, LocalDateTime date, BigDecimal cost, UserDto user,
			List<GiftCertificateOrderDto> giftCertificateOrderList) {
		super();
		this.id = id;
		this.date = date;
		this.cost = cost;
		this.giftCertificateOrderList = giftCertificateOrderList;
	}

	public void addGiftCertificate(GiftCertificateOrderDto giftCertificate) {
		if (giftCertificateOrderList == null) {
			giftCertificateOrderList = new ArrayList<>();
		}
		giftCertificateOrderList.add(giftCertificate);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public List<GiftCertificateOrderDto> getGiftCertificateOrderList() {
		return giftCertificateOrderList;
	}

	public void setGiftCertificateOrderList(List<GiftCertificateOrderDto> giftCertificateOrderList) {
		this.giftCertificateOrderList = giftCertificateOrderList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cost, date, giftCertificateOrderList, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDto other = (OrderDto) obj;
		return Objects.equals(cost, other.cost) && Objects.equals(date, other.date)
				&& Objects.equals(giftCertificateOrderList, other.giftCertificateOrderList) && id == other.id;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("OrderDTO{ id=").append(id);
		sb.append(", date=").append(date);
		sb.append(", cost=").append(cost).append(" }");
		return sb.toString();
	}
}
