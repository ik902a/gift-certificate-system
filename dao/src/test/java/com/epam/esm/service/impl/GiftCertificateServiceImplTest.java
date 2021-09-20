package com.epam.esm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.esm.configuration.ModelConfiguration;
import com.epam.esm.configuration.TestConfiguration;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ResourceNotExistException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles(value = "dev")
public class GiftCertificateServiceImplTest {
	public static Logger log = LogManager.getLogger();
	GiftCertificate giftCertificate;
	GiftCertificate giftCertificateUpdate;
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
       
        giftCertificateUpdate = new GiftCertificate();
        giftCertificateUpdate.setId(1L);
        giftCertificateUpdate.setName("NameUpdate");
        giftCertificateUpdate.setDescription("Some description 1");
        giftCertificateUpdate.setPrice(new BigDecimal("50"));
        giftCertificateUpdate.setDuration(90);
        giftCertificateUpdate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        giftCertificateUpdate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
    }
    
    @AfterEach
    void tearDown() {
        giftCertificate = null;
        session.finishMocking();
    }
    
//    @Test
//    public void createPositiveTest() {
//        when(giftCertificateDao.create(isA(GiftCertificate.class))).thenReturn(giftCertificate);
//        GiftCertificate actual = giftCertificateService.create(giftCertificate);
//        assertEquals(giftCertificate, actual);
//    }
    
//    @Test
//    public void findPositiveTest() {
//        when(giftCertificateDao.find()).thenReturn(List.of(giftCertificate));
//        List<GiftCertificate> actual = giftCertificateService.find(Collections.<String, String>emptyMap());
//        assertEquals(List.of(giftCertificate), actual);
//    }

//    @Test
//    public void findByIdPositiveTest() {
//        when(giftCertificateDao.findEntityById(anyLong())).thenReturn(Optional.of(giftCertificate));
//        GiftCertificate actual = giftCertificateService.findById(1);
//        assertEquals(giftCertificate, actual);
//    }
    
//    @Test
//    public void findByIdNegativeTest() {
//        when(giftCertificateDao.findEntityById(anyLong())).thenReturn(Optional.empty());
//        assertThrows(ResourceException.class, () -> giftCertificateService.findById(42));
//    }
//    
//    @Test
//    public void updatePositiveTest() {
//    	when(giftCertificateDao.findEntityById(anyLong())).thenReturn(Optional.of(giftCertificate));
//        when(giftCertificateDao.update(isA(GiftCertificate.class))).thenReturn(giftCertificateUpdate);
//        GiftCertificate actual = giftCertificateService.update(giftCertificateUpdate);
//        assertEquals(giftCertificateUpdate, actual);
//    }
//
//    @Test
//    public void updateNegativeTest() {
//        when(giftCertificateDao.findEntityById(anyLong())).thenReturn(Optional.empty());
//        assertThrows(ResourceException.class,
//                () -> giftCertificateService.update(giftCertificate));
//    }
//    
//    @Test
//    public void deletePositiveTest() {
//        when(giftCertificateDao.delete(anyLong())).thenReturn(true);
//        giftCertificateService.delete(1);
//        verify(giftCertificateDao, times(1)).delete(anyLong());
//    }
//
//    @Test
//    public void deleteNegativeTest() {
//        when(giftCertificateDao.delete(anyLong())).thenReturn(false);
//        assertThrows(ResourceException.class, () -> giftCertificateService.delete(42));
//    }
}
