package com.epam.esm.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The {@code OrderResponse} class describes the response order
 * 
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class OrderResponse extends RepresentationModel<OrderResponse> {
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime date;
	private BigDecimal cost;
	private List<GiftCertificateOrderResponse> giftCertificateOrderList;

    /**
	 * Constructs a new response
	 */
	public OrderResponse() {
		super();
	}

	/**
	 * Constructs a new response with the specified
	 * 
	 * @param id is order id
	 * @param date {@link LocalDateTime} creating date
	 * @param cost {@link BigDecimal} cost order
	 * @param giftCertificateOrderList {@link List} of {@link GiftCertificateOrderResponse} is list 
	 * gift certificates
	 */
	public OrderResponse(long id, LocalDateTime date, BigDecimal cost,
			List<GiftCertificateOrderResponse> giftCertificateOrderList) {
		super();
		this.id = id;
		this.date = date;
		this.cost = cost;
		this.giftCertificateOrderList = giftCertificateOrderList;
	}

	/**
	 * Builds a new response
	 * 
	 * @param order {@link OrderDto} entity
	 * @return {@link OrderResponse} response
	 */
	public static OrderResponse valueOf(OrderDto order) {// TODO check
		List<GiftCertificateOrderResponse> giftCertificateOrderResponse = (order.getGiftCertificateOrderList() != null)
				? order.getGiftCertificateOrderList().stream()
						.map(giftCertificateOrder -> GiftCertificateOrderResponse.valueOf(giftCertificateOrder))
						.collect(Collectors.toList())
				: Collections.emptyList();
		return new OrderResponse(order.getId(), order.getDate(), order.getCost(), giftCertificateOrderResponse);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<GiftCertificateOrderResponse> getGiftCertificateOrderList() {
		return giftCertificateOrderList;
	}

	public void setGiftCertificateOrderList(List<GiftCertificateOrderResponse> giftCertificateOrderList) {
		this.giftCertificateOrderList = giftCertificateOrderList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cost, date, giftCertificateOrderList, id);
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
		OrderResponse other = (OrderResponse) obj;
		return Objects.equals(cost, other.cost) && Objects.equals(date, other.date)
				&& Objects.equals(giftCertificateOrderList, other.giftCertificateOrderList) && id == other.id;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nOrderResponse{ id=").append(id);
		sb.append(", date=").append(date);
		sb.append(", cost=").append(cost).append(" }");
		return sb.toString();
	}
}
