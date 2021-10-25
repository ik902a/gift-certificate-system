package com.epam.esm.response;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class OrderResponse extends RepresentationModel<OrderResponse> {
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime date;
	private BigDecimal cost;
	private List<GiftCertificateOrderResponse> giftCertificateOrderList;

//	public OrderResponse(long id, ZonedDateTime date, BigDecimal cost,
//			List<GiftCertificateOrderResponse> giftCertificateOrderList) {
//		super();
//		this.id = id;
//		this.date = date;
//		this.cost = cost;
//		this.giftCertificateOrderList = giftCertificateOrderList;
//	}

	public static OrderResponse valueOf(OrderDto order) {
		List<GiftCertificateOrderResponse> giftCertificateOrderResponse = order.getGiftCertificateOrderList()
				.stream()
				.map(giftCertificateOrder -> GiftCertificateOrderResponse.valueOf(giftCertificateOrder))
				.collect(Collectors.toList());
		return new OrderResponse(order.getId(), order.getDate(), order.getCost(), giftCertificateOrderResponse);
	}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public ZonedDateTime getDate() {
//		return date;
//	}
//
//	public void setDate(ZonedDateTime date) {
//		this.date = date;
//	}
//
//	public BigDecimal getCost() {
//		return cost;
//	}
//
//	public void setCost(BigDecimal cost) {
//		this.cost = cost;
//	}
//
//	public List<GiftCertificateOrderResponse> getGiftCertificateOrderList() {
//		return giftCertificateOrderList;
//	}
//
//	public void setGiftCertificateOrderList(List<GiftCertificateOrderResponse> giftCertificateOrderList) {
//		this.giftCertificateOrderList = giftCertificateOrderList;
//	}
}
