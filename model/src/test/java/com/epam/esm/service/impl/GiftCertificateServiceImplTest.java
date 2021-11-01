package com.epam.esm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;
import com.epam.esm.configuration.ModelConfiguration;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.PageDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ResourceNotExistException;
import com.epam.esm.service.GiftCertificateService;

@SpringBootTest (classes = ModelConfiguration.class)
public class GiftCertificateServiceImplTest {
	public static Logger log = LogManager.getLogger();
	@MockBean
	GiftCertificateDao giftCertificateDao;
	@MockBean
	TagDao tagDao; 
	
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public GiftCertificateService giftCertificateService() {
            return new GiftCertificateServiceImpl();
        }
    }

    @Autowired
	GiftCertificateServiceImpl giftCertificateService;
    
    @Test
    public void createPositiveTest() {
    	GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setName("Name1");
        giftCertificateDto.setDescription("Some description 1");
        giftCertificateDto.setPrice(new BigDecimal("50"));
        giftCertificateDto.setDuration(90);
        giftCertificateDto.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        giftCertificateDto.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1L);
        giftCertificate.setName("Name1");
        giftCertificate.setDescription("Some description 1");
        giftCertificate.setPrice(new BigDecimal("50"));
        giftCertificate.setDuration(90);
        giftCertificate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        giftCertificate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        GiftCertificateDto giftCertificateDtoResult = giftCertificateDto;
        giftCertificateDtoResult.setId(1L);
        
        when(giftCertificateDao.create(isA(GiftCertificate.class))).thenReturn(giftCertificate);
        GiftCertificateDto actual = giftCertificateService.create(giftCertificateDto);
        assertEquals(giftCertificateDtoResult, actual);
    }
    
    @Test
    public void findPositiveTest() {
    	GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1L);
        giftCertificate.setName("Name1");
        giftCertificate.setDescription("Some description 1");
        giftCertificate.setPrice(new BigDecimal("50"));
        giftCertificate.setDuration(90);
        giftCertificate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        giftCertificate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setId(1L);
        giftCertificateDto.setName("Name1");
        giftCertificateDto.setDescription("Some description 1");
        giftCertificateDto.setPrice(new BigDecimal("50"));
        giftCertificateDto.setDuration(90);
        giftCertificateDto.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        giftCertificateDto.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        PageDto<GiftCertificateDto> page = new PageDto<>();
        page.setContent(List.of(giftCertificateDto));
        page.setTotalPages(1L);
        page.setPageNumber(1L);
        page.setLimit(5);
        page.setOffset(0);
        
        when(giftCertificateDao.find(anyMap())).thenReturn(List.of(giftCertificate));
        PageDto<GiftCertificateDto> actual = giftCertificateService.find(Collections.<String, String>emptyMap());
        assertEquals(page, actual);
    }

    @Test
    public void findByIdPositiveTest() {
    	GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1L);
        giftCertificate.setName("Name1");
        giftCertificate.setDescription("Some description 1");
        giftCertificate.setPrice(new BigDecimal("50"));
        giftCertificate.setDuration(90);
        giftCertificate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        giftCertificate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setId(1L);
        giftCertificateDto.setName("Name1");
        giftCertificateDto.setDescription("Some description 1");
        giftCertificateDto.setPrice(new BigDecimal("50"));
        giftCertificateDto.setDuration(90);
        giftCertificateDto.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        giftCertificateDto.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
        
        when(giftCertificateDao.findEntityById(anyLong())).thenReturn(Optional.of(giftCertificate));
        GiftCertificateDto actual = giftCertificateService.findById(1);
        assertEquals(giftCertificateDto, actual);
    }
    
    @Test
    public void findByIdNegativeTest() {
		when(giftCertificateDao.findEntityById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ResourceNotExistException.class, () -> giftCertificateService.findById(42));
	}

//	@Test
//	public void updatePositiveTest() {
//		GiftCertificate giftCertificate = new GiftCertificate();
//		giftCertificate.setId(1L);
//		giftCertificate.setName("Name1");
//		giftCertificate.setDescription("Some description 1");
//		giftCertificate.setPrice(new BigDecimal("50"));
//		giftCertificate.setDuration(90);
//		giftCertificate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
//		giftCertificate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
//		GiftCertificate giftCertificateUpdate = new GiftCertificate();
//		giftCertificateUpdate.setId(1L);
//		giftCertificateUpdate.setName("NameUpdate");
//		giftCertificateUpdate.setDescription("Some description 1");
//		giftCertificateUpdate.setPrice(new BigDecimal("50"));
//		giftCertificateUpdate.setDuration(90);
//		giftCertificateUpdate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
//		giftCertificateUpdate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
//		GiftCertificateDto giftCertificateDtoUpdate = new GiftCertificateDto();
//		giftCertificateDtoUpdate.setId(1L);
//		giftCertificateDtoUpdate.setName("NameUpdate");
//		giftCertificateDtoUpdate.setDescription("Some description 1");
//		giftCertificateDtoUpdate.setPrice(new BigDecimal("50"));
//		giftCertificateDtoUpdate.setDuration(90);
//		giftCertificateDtoUpdate.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
//		giftCertificateDtoUpdate.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
//
//		when(giftCertificateDao.findEntityById(anyLong())).thenReturn(Optional.of(giftCertificate));
//		when(giftCertificateDao.update(isA(GiftCertificate.class))).thenReturn(giftCertificateUpdate);
//		GiftCertificateDto actual = giftCertificateService.update(giftCertificateDtoUpdate);
//		assertEquals(giftCertificateDtoUpdate, actual);
//	}

	@Test
	public void updateNegativeTest() {
		GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
		giftCertificateDto.setId(1L);
		giftCertificateDto.setName("Name1");
		giftCertificateDto.setDescription("Some description 1");
		giftCertificateDto.setPrice(new BigDecimal("50"));
		giftCertificateDto.setDuration(90);
		giftCertificateDto.setCreateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		giftCertificateDto.setLastUpdateDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));

		when(giftCertificateDao.findEntityById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ResourceNotExistException.class, () -> giftCertificateService.update(giftCertificateDto));
	}
    
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
//        assertThrows(ResourceNotExistException.class, () -> giftCertificateService.delete(42));
//    }
}
