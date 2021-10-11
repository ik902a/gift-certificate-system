package com.epam.esm.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The {@code GiftCertificate} class describes the entity gift certificate
 * 
 * @author Ihar Klepcha
 * @see Entity
 */
@Entity
@Table(name="gift_certificates")
public class GiftCertificate extends AbstractEntity {
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="price")
	private BigDecimal price;
	@Column(name="duration")
	private int duration;
	@Column(name = "create_date")
	private ZonedDateTime createDate;
	@Column(name = "last_update_date")
	private ZonedDateTime lastUpdateDate;
	@ManyToMany         
	@JoinTable(name = "gift_certificates_tags"
		, joinColumns = @JoinColumn(name = "gift_certificate_id")
		, inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;
	
	@OneToMany(mappedBy = "giftCertificate")    
	private List<GiftCertificateOrder> giftCertificateOrderList;
	
	/**
	 * Constructs a new gift certificate
	 */
	public GiftCertificate() {
	}

	/**
	 * Constructs a new gift certificate with the specified
	 * 
	 * @param id is gift certificate id
	 * @param name {@link String} name
	 * @param description {@link String} description
	 * @param price {@link BigDecimal} price
	 * @param duration is duration
	 * @param createDate {@link ZonedDateTime} create date
	 * @param lastUodateDate {@link ZonedDateTime} last update date
	 * @param tags {@link List} of ({@link Tag} list tags
	 * @param giftCertificateOrderList {@link List} of ({@link GiftCertificateOrder} list giftCertificateOrder
	 */
	public GiftCertificate(long id, String name, String description, BigDecimal price, int duration,
			ZonedDateTime createDate, ZonedDateTime lastUpdateDate, List<Tag> tags,
			List<GiftCertificateOrder> giftCertificateOrderList) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.duration = duration;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
		this.tags = tags;
		this.giftCertificateOrderList = giftCertificateOrderList;
	}

	public void addTag(Tag tag) {
		if (tags == null) {
			tags = new ArrayList<>();
		}
		tags.add(tag);
	}

	public void addGiftCertificateOrder(GiftCertificateOrder giftCertificateOrder) {
		if (giftCertificateOrderList == null) {
			giftCertificateOrderList = new ArrayList<>();
		}
		giftCertificateOrderList.add(giftCertificateOrder);
		giftCertificateOrder.setGiftCertificate(this);
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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<GiftCertificateOrder> getGiftCertificateOrderList() {
		return giftCertificateOrderList;
	}

	public void setGiftCertificateOrderList(List<GiftCertificateOrder> giftCertificateOrderList) {
		this.giftCertificateOrderList = giftCertificateOrderList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + duration;
		result = prime * result + ((giftCertificateOrderList == null) ? 0 : giftCertificateOrderList.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiftCertificate other = (GiftCertificate) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (duration != other.duration)
			return false;
		if (giftCertificateOrderList == null) {
			if (other.giftCertificateOrderList != null)
				return false;
		} else if (!giftCertificateOrderList.equals(other.giftCertificateOrderList))
			return false;
		if (id != other.id)
			return false;
		if (lastUpdateDate == null) {
			if (other.lastUpdateDate != null)
				return false;
		} else if (!lastUpdateDate.equals(other.lastUpdateDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nGift certificate{ ID=").append(id);
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
