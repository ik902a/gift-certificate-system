package com.epam.esm.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.configuration.TestConfiguration;
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
	public void findEntityByIdPositiveTest() {
		Order order = new Order();
		order.setDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		order.setCost(new BigDecimal("55.55"));
		order.setId(1L);
		GiftCertificateOrder giftCertificateOrder = new GiftCertificateOrder();
		GiftCertificateOrderKey key = new GiftCertificateOrderKey(1L, 2L);
		giftCertificateOrder.setId(key);
		GiftCertificate giftCertificate = new GiftCertificate();
		giftCertificate.setId(2L);
		giftCertificate.setName("Second");
		giftCertificate.setDescription("Some description 2");
		giftCertificate.setPrice(new BigDecimal("70"));
		giftCertificate.setDuration(42);
		giftCertificate.setCreateDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		giftCertificate.setLastUpdateDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		giftCertificateOrder.setGiftCertificate(giftCertificate);
		giftCertificateOrder.setOrder(order);
		giftCertificateOrder.setQuantity(3);
		order.setGiftCertificateOrderList(List.of(giftCertificateOrder));
		
		Optional<Order> actual = orderDao.findEntityById(1);
		
		assertEquals(Optional.of(order), actual);
	}

	@Test
	public void findOrdersByUserTest() {
		Order order = new Order();
		order.setId(3L);
		order.setDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		order.setCost(new BigDecimal("65.65"));
		GiftCertificateOrder giftCertificateOrder = new GiftCertificateOrder();
		GiftCertificateOrderKey key = new GiftCertificateOrderKey(3L, 2L);
		giftCertificateOrder.setId(key);
		GiftCertificate giftCertificate = new GiftCertificate();
		giftCertificate.setId(2L);
		giftCertificate.setName("Second");
		giftCertificate.setDescription("Some description 2");
		giftCertificate.setPrice(new BigDecimal("70"));
		giftCertificate.setDuration(42);
		giftCertificate.setCreateDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		giftCertificate.setLastUpdateDate(LocalDateTime.parse("2021-08-12T08:12:15"));
		giftCertificateOrder.setGiftCertificate(giftCertificate);
		giftCertificateOrder.setOrder(order);
		giftCertificateOrder.setQuantity(2);
		order.setGiftCertificateOrderList(List.of(giftCertificateOrder));
		User user = new User();
		user.setId(2L);
		
		List<Order> actual = orderDao.findOrdersByUser(user, new HashMap<String, String>());
		
		assertEquals(List.of(order), actual);
	}
}
