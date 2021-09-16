package com.epam.esm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.quality.Strictness;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.esm.configuration.ModelConfiguration;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelConfiguration.class)
public class GiftCertificateServiceImplTest {
	public static Logger log = LogManager.getLogger();
	GiftCertificate giftCertificate;
	MockitoSession session;
	
	@Mock
	GiftCertificateDao giftCertificateDao;
	@Mock
	TagDao tagDao;
	    
	@InjectMocks
	GiftCertificateServiceImpl giftCertificateService = new GiftCertificateServiceImpl();

    @BeforeEach
    public void setUp() {
	    session = Mockito.mockitoSession()
	            .initMocks(this)
	            .strictness(Strictness.LENIENT)
	            .startMocking();
        giftCertificate = new GiftCertificate();
        giftCertificate.setId(1L);
        giftCertificate.setName("Name1");
        giftCertificate.setDescription("Some description 1");
        giftCertificate.setPrice(new BigDecimal("50"));
        giftCertificate.setDuration(90);
        giftCertificate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        giftCertificate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
    }
    
    @AfterEach
    void tearDown() {
        giftCertificate = null;
        session.finishMocking();
    }

    @Test
    public void findByIdPositiveTest() {
        when(giftCertificateDao.findEntityById(anyLong())).thenReturn(Optional.of(giftCertificate));
        GiftCertificate actual = giftCertificateService.findById(1);
        assertEquals(giftCertificate, actual);
    }
}
