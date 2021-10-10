package com.epam.esm.service.impl;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.OrderDataDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateOrder;
import com.epam.esm.entity.GiftCertificateOrderKey;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.OrderService;

/**
 * The {@code OrderServiceImpl} class is responsible for operations with order
 * 
 * @author Ihar Klepcha
 * @see GiftCertificateService
 */
@Service
public class OrderServiceImpl implements OrderService {
	public static Logger log = LogManager.getLogger();
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private GiftCertificateDao giftCertificateDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public OrderDto create(OrderDataDto orderDataDto) {
		log.info("CREATE Order Service {}", orderDataDto);
		Order order = buildOrder(orderDataDto);
		order = orderDao.create(order);
		addGiftCertificateOrderData(order);
		OrderDto orderDto = modelMapper.map(order, OrderDto.class);
		orderDto.setGiftCertificateList(addGiftCertificatesDto(order));
		return orderDto;
	}

	private Order buildOrder(OrderDataDto orderDataDto) {
		Order order = new Order();
		User user = userDao.findEntityById(orderDataDto.getUserId());
		order.setUser(user);
		BigDecimal cost = BigDecimal.ZERO;
		Iterator<Map.Entry<Long, Integer>> giftCertificates = orderDataDto.getGiftCertificateMap().entrySet()
				.iterator();
		while (giftCertificates.hasNext()) {
			Map.Entry<Long, Integer> giftCertificateData = giftCertificates.next();
			GiftCertificate giftCertificate = giftCertificateDao.findEntityById(giftCertificateData.getKey());
			int quantity = giftCertificateData.getValue();
			order.addGiftCertificateOrder(new GiftCertificateOrder(order, giftCertificate, quantity));
			cost = cost.add(giftCertificate.getPrice());
		}
		order.setCost(cost);
		ZonedDateTime currentDate = ZonedDateTime.now();
		order.setDate(currentDate);
		return order;
	}

	private void addGiftCertificateOrderData(Order order) {
		order.getGiftCertificateOrderList().stream()
			.forEach(giftCertificateOrder -> { giftCertificateOrder.setId(
					new GiftCertificateOrderKey(order.getId(), giftCertificateOrder.getGiftCertificate().getId()));
			orderDao.createGiftCertificateOrder(giftCertificateOrder); });		
	}
	
	private List<GiftCertificateDto> addGiftCertificatesDto(Order order) {
		List<GiftCertificateDto> giftCertificateList = order.getGiftCertificateOrderList().stream()
			.map(giftCertificateOrder -> modelMapper.map(
					giftCertificateOrder.getGiftCertificate(), GiftCertificateDto.class))
			.collect(Collectors.toList());
		
//		for (GiftCertificateOrder giftCertificateOrder : order.getGiftCertificateOrderList()) {
//			GiftCertificate giftCertificate = giftCertificateOrder.getGiftCertificate();
//			GiftCertificateDto giftCertificateDto = modelMapper.map(giftCertificate, GiftCertificateDto.class));
//			orderDto.addGiftCertificate(giftCertificateDto);
//		}
		return giftCertificateList;
	}
	
	@Transactional
	@Override
	public OrderDto findById(long id) {
		log.info("FIND Order BY ID Service id={}", id);
		Order order = orderDao.findEntityById(id);
		OrderDto orderDto = modelMapper.map(order, OrderDto.class);
		orderDto.setGiftCertificateList(addGiftCertificatesDto(order));
		return orderDto;
	}
}
