package com.epam.esm.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderDto extends RepresentationModel<OrderDto> {
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime date;
	private BigDecimal cost;
	private UserInOrderDto user;
	private List<GiftCertificateDto> giftCertificates;
	
	public OrderDto() {
		super();
	}

	public void addGiftCertificate(GiftCertificateDto giftCertificate) {
        if (giftCertificates == null) {
        	giftCertificates = new ArrayList<>();
        }
        giftCertificates.add(giftCertificate);
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
	
	public List<GiftCertificateDto> getGiftCertificates() {
		return giftCertificates;
	}

	public void setGiftCertificates(List<GiftCertificateDto> giftCertificates) {
		this.giftCertificates = giftCertificates;
	}



    


//	@Override
//	public String toString() {
//		final StringBuilder sb = new StringBuilder();
//		sb.append("\nOrderDTO{ id=").append(id);
//		sb.append(", date=").append(date);
//		sb.append(", cost=").append(cost).append(" }");
//		return sb.toString();
//	}
}
