package com.epam.esm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
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
@Table(name = "gift_certificates")
@EntityListeners(GiftCertificateAudit.class)
public class GiftCertificate extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "duration")
	private Integer duration;
	@Column(name = "create_date")
	private LocalDateTime createDate;
	@Column(name = "last_update_date")
	private LocalDateTime lastUpdateDate;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "gift_certificates_tags", 
		joinColumns = @JoinColumn(name = "gift_certificate_id"), 
		inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;
	@OneToMany(mappedBy = "giftCertificate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<GiftCertificateOrder> giftCertificateOrderList;

	/**
	 * Constructs a gift certificate
	 */
	public GiftCertificate() {
	}

	/**
	 * Constructs a gift certificate with the specified
	 * 
	 * @param id                       {@link Long} gift certificate id
	 * @param name                     {@link String} name
	 * @param description              {@link String} description
	 * @param price                    {@link BigDecimal} price
	 * @param duration                 {@link Integer} duration
	 * @param createDate               {@link LocalDateTime} create date
	 * @param lastUodateDate           {@link LocalDateTime} last update date
	 * @param tags                     {@link List} of ({@link Tag} list tags
	 * @param giftCertificateOrderList {@link List} of ({@link GiftCertificateOrder}
	 *                                 list GiftCertificateOrders
	 */
	public GiftCertificate(Long id, String name, String description, BigDecimal price, Integer duration,
			LocalDateTime createDate, LocalDateTime lastUpdateDate, List<Tag> tags,
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
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
		sb.append("Gift certificate{ ID=").append(id);
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
