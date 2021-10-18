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
	
    @PrePersist
    public void beforeCreateGiftCertificate(GiftCertificate giftCertificate) {
        ZonedDateTime currentDate = ZonedDateTime.now();
        giftCertificate.setCreateDate(currentDate);
        giftCertificate.setLastUpdateDate(currentDate);
    }

    @PreUpdate
    public void beforeUpdateGiftCertificate(GiftCertificate giftCertificate) {
        giftCertificate.setLastUpdateDate(ZonedDateTime.now());
    }

}
