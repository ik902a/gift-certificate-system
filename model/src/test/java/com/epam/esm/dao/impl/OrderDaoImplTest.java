package com.epam.esm.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.configuration.TestConfiguration;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateOrder;
import com.epam.esm.entity.GiftCertificateOrderKey;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;

@SpringBootTest(classes = TestConfiguration.class)
@Transactional
public class OrderDaoImplTest {
	public static Logger log = LogManager.getLogger();

	@Autowired
	private OrderDao orderDao;
	
	@Test
	public void createPositiveTest() {
		Order orderNew = new Order();
		User user = new User();
		user.setId(1L);
		user.setLogin("user1");
		GiftCertificate giftCertificate = new GiftCertificate();
		giftCertificate.setId(1L);
		
		GiftCertificateOrder giftCertificateOrder = new GiftCertificateOrder();
		giftCertificateOrder.setGiftCertificate(giftCertificate);
		GiftCertificateOrderKey key = new GiftCertificateOrderKey();
		key.setGiftCertificateId(giftCertificate.getId());
		giftCertificateOrder.setId(key);
		giftCertificateOrder.setGiftCertificate(giftCertificate);
		giftCertificateOrder.setOrder(orderNew);
		
		orderNew.setCost(new BigDecimal(44.44));
		orderNew.setUser(user);
		orderNew.setGiftCertificateOrderList(List.of(giftCertificateOrder));
		Order orderCreated = new Order();
		orderCreated.setId(4L);
		orderCreated.setDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		orderCreated.setCost(new BigDecimal(44.44));
		orderCreated.setUser(user);
		
		Order actual = orderDao.create(orderNew);
		actual.setDate(ZonedDateTime.parse("2021-08-12T08:12:15+03:00"));
		
		assertEquals(orderCreated, actual);
	}

}
