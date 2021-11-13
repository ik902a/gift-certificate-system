package com.epam.esm.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.GiftCertificateDto;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The {@code GiftCertificateResponse} class describes the response certificate
 * 
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class GiftCertificateResponse extends RepresentationModel<GiftCertificateResponse> {
	private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastUpdateDate;
    private List<TagResponse> tags;
    
    /**
	 * Constructs a new response
	 */
	public GiftCertificateResponse() {
		super();
	}

	/**
	 * Constructs a new response with the specified
	 * 
	 * @param id is gift certificate id
	 * @param name {@link String} name
	 * @param description {@link String} description
	 * @param price {@link BigDecimal} price
	 * @param duration is duration
	 * @param createDate {@link LocalDateTime} create date
	 * @param lastUodateDate {@link LocalDateTime} last update date
	 * @param tags {@link List} of ({@link TagResponse} list tags
	 */
    public GiftCertificateResponse(long id, String name, String description, BigDecimal price, int duration,
    		LocalDateTime createDate, LocalDateTime lastUpdateDate, List<TagResponse> tags) {
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
    
	/**
	 * Builds a new response
	 * 
	 * @param giftCertificate {@link GiftCertificateDto} entity
	 * @return {@link GiftCertificateResponse} response
	 */
	public static GiftCertificateResponse valueOf(GiftCertificateDto giftCertificate) {
		List<TagResponse> tagResponse = (giftCertificate.getTags() != null) // TODO check
				? giftCertificate.getTags()
						.stream()
						.map(tag -> TagResponse.valueOf(tag))
						.collect(Collectors.toList())
				: Collections.emptyList();
		return new GiftCertificateResponse(giftCertificate.getId(),
				giftCertificate.getName()
    			, giftCertificate.getDescription()
    			, giftCertificate.getPrice()
    			, giftCertificate.getDuration()
    			, giftCertificate.getCreateDate()
    			, giftCertificate.getLastUpdateDate()
    			, tagResponse);
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

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public List<TagResponse> getTags() {
		return tags;
	}

	public void setTags(List<TagResponse> tags) {
		this.tags = tags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(createDate, description, duration, id, lastUpdateDate, name, price, tags);
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
		GiftCertificateResponse other = (GiftCertificateResponse) obj;
		return Objects.equals(createDate, other.createDate) && Objects.equals(description, other.description)
				&& duration == other.duration && id == other.id && Objects.equals(lastUpdateDate, other.lastUpdateDate)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& Objects.equals(tags, other.tags);
	}
    
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nGiftCertificateResponse{ ID=").append(id);
		sb.append(", name=").append(name);
		sb.append(", description=").append(description);
		sb.append(", price=").append(price);
		sb.append(", duration= ").append(duration);
		sb.append(", create_date=").append(createDate);
		sb.append(", last_update_date=").append(lastUpdateDate);
		sb.append(", tags=").append(tags).append(" }");
		return sb.toString();
	}
}
