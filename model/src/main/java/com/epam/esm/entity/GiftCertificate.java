package com.epam.esm.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.epam.esm.entity.audit.GiftCertificateAudit;

/**
 * The {@code GiftCertificate} class describes the entity gift certificate
 * 
 * @author Ihar Klepcha
 * @see AbstractEntity
 */
@Entity
@Table(name="gift_certificates")
@EntityListeners(GiftCertificateAudit.class)
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
