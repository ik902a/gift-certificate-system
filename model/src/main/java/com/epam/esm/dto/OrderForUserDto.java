package com.epam.esm.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderForUserDto extends RepresentationModel<OrderForUserDto> {
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime date;
	private BigDecimal cost;
//	private List<GiftCertificateDto> giftCertificates;
	
	
//
//	public void addGiftCertificate(GiftCertificateDto giftCertificate) {
//        if (giftCertificates == null) {
//        	giftCertificates = new ArrayList<>();
//        }
//        giftCertificates.add(giftCertificate);
//    }

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

//	public List<GiftCertificateDto> getGiftCertificates() {
//		return giftCertificates;
//	}
//
//	public void setGiftCertificates(List<GiftCertificateDto> giftCertificates) {
//		this.giftCertificates = giftCertificates;
//	}
	
	
}
