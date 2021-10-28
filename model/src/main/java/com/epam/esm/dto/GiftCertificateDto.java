package com.epam.esm.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.entity.GiftCertificateOrder;
import com.epam.esm.entity.Tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The {@code GiftCertificateDto} class is implementation of pattern DTO for transmission Gift Certificate
 * entity between service and controller.
 *
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GiftCertificateDto {
	private Long id;
	@NotBlank
    @Size(max = 45)
    private String name;
	@NotBlank
    @Size(max = 100)
    private String description;
	@NotNull
	@Digits(integer = 4, fraction = 2)
    private BigDecimal price;
	@DecimalMin(value = "1")
	@DecimalMax(value = "365")
	@NotNull
    private int duration;
    private ZonedDateTime createDate;
    private ZonedDateTime lastUpdateDate;
    @Valid
    private List<TagDto> tags;
    
    /**
	 * Constructs a new Gift Certificate DTO
	 */
	public GiftCertificateDto() {
		super();
	}

	/**
	 * Constructs a new gift certificate DTO with the specified
	 * 
	 * @param id {@link Long} gift certificate id
	 * @param name {@link String} name
	 * @param description {@link String} description
	 * @param price {@link BigDecimal} price
	 * @param duration is duration
	 * @param createDate {@link ZonedDateTime} create date
	 * @param lastUodateDate {@link ZonedDateTime} last update date
	 * @param tags {@link List} of ({@link Tag} list tags
	 * @param giftCertificateOrderList {@link List} of ({@link GiftCertificateOrder} list giftCertificateOrder
	 */
    public GiftCertificateDto(Long id, String name, String description, BigDecimal price, int duration, 
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

	public void addTag(TagDto tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tag);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public int hashCode() {
		return Objects.hash(createDate, description, duration, id, lastUpdateDate, name, price, tags);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiftCertificateDto other = (GiftCertificateDto) obj;
		return Objects.equals(createDate, other.createDate) && Objects.equals(description, other.description)
				&& duration == other.duration && Objects.equals(id, other.id)
				&& Objects.equals(lastUpdateDate, other.lastUpdateDate) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price) && Objects.equals(tags, other.tags);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nGift Certificate DTO{ id=").append(id);
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
