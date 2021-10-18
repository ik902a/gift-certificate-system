package com.epam.esm.entity.audit;

import java.time.ZonedDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.epam.esm.entity.GiftCertificate;

/**
 * Class is listener for gift certificate
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
        ZonedDateTime currentDate = ZonedDateTime.now();
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
        giftCertificate.setLastUpdateDate(ZonedDateTime.now());
    }
}
