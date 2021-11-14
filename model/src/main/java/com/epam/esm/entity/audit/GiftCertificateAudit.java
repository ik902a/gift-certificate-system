package com.epam.esm.entity.audit;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.epam.esm.entity.GiftCertificate;

/**
 * The {@code GiftCertificateAudit} class is listener for gift certificate
 *
 * @author Ihar Klepcha
 */
public class GiftCertificateAudit {

	/**
	 * Adds date before creating certificate
	 * 
	 * @param giftCertificate {@link GiftCertificate} gift certificate
	 */
	@PrePersist
	public void beforeCreateGiftCertificate(GiftCertificate giftCertificate) {
		LocalDateTime currentDate = LocalDateTime.now();
		giftCertificate.setCreateDate(currentDate);
		giftCertificate.setLastUpdateDate(currentDate);
	}

	/**
	 * Adds date before update certificate
	 * 
	 * @param giftCertificate {@link GiftCertificate} gift certificate
	 */
	@PreUpdate
	public void beforeUpdateGiftCertificate(GiftCertificate giftCertificate) {
		giftCertificate.setLastUpdateDate(LocalDateTime.now());
	}
}
