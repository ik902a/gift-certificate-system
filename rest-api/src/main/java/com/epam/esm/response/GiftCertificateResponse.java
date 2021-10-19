package com.epam.esm.response;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.TagDto;
import com.fasterxml.jackson.annotation.JsonFormat;

public class GiftCertificateResponse extends RepresentationModel<GiftCertificateResponse> {
	private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime lastUpdateDate;
    private List<TagDto> tags;
	
    public GiftCertificateResponse(long id, String name, String description, BigDecimal price, int duration,
			ZonedDateTime createDate, ZonedDateTime lastUpdateDate, List<TagDto> tags) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.duration = duration;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
		this.tags = tags;
	}
    
    public static GiftCertificateResponse valueOf(GiftCertificateDto giftCertificate) {
    	return new GiftCertificateResponse(giftCertificate.getId()
    			, giftCertificate.getName()
    			, giftCertificate.getDescription()
    			, giftCertificate.getPrice()
    			, giftCertificate.getDuration()
    			, giftCertificate.getCreateDate()
    			, giftCertificate.getLastUpdateDate()
    			, giftCertificate.getTags());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ZonedDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(ZonedDateTime createDate) {
		this.createDate = createDate;
	}

	public ZonedDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(ZonedDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public List<TagDto> getTags() {
		return tags;
	}

	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}
    
}
