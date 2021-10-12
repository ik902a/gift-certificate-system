package com.epam.esm.service.impl;

import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.epam.esm.exception.ResourceNotExistException;
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
//		addGiftCertificateOrderData(order);
		OrderDto orderDto = modelMapper.map(order, OrderDto.class);
		orderDto.setGiftCertificates(addGiftCertificatesDto(order));
		return orderDto;
	}

	private Order buildOrder(OrderDataDto orderDataDto) {
		Order order = new Order();
		Optional<User> userOptional = userDao.findEntityById(orderDataDto.getUserId());
		User user = userOptional.orElseThrow(
			() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
					, orderDataDto.getUserId()
					, USER_INCORRECT.getErrorCode()));
		order.setUser(user);
		BigDecimal cost = BigDecimal.ZERO;
		Iterator<Map.Entry<Long, Integer>> giftCertificates = orderDataDto.getGiftCertificateMap().entrySet()
				.iterator();
		while (giftCertificates.hasNext()) {
			Map.Entry<Long, Integer> giftCertificateData = giftCertificates.next();
			Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findEntityById(
					giftCertificateData.getKey());
			GiftCertificate giftCertificate = giftCertificateOptional.orElseThrow(
					() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
							, giftCertificateData.getKey()
							, GIFT_CERTIFICATE_INCORRECT.getErrorCode()));
			int quantity = giftCertificateData.getValue();
			order.addGiftCertificateOrder(new GiftCertificateOrder(order, giftCertificate, quantity));
			cost = cost.add(giftCertificate.getPrice());
		}
		log.info("CREATE GCOrder Service {}------", order.getGiftCertificateOrderList().get(0).getOrder().getCost());
		order.setCost(cost);
		log.info("CREATE GCOrder Service {}------", order.getGiftCertificateOrderList().get(0).getOrder().getCost());
		ZonedDateTime currentDate = ZonedDateTime.now();
		order.setDate(currentDate);
		return order;
	}

	private void addGiftCertificateOrderData(Order order) {//TODO incorrect adding
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
		Optional<Order> orderOptional = orderDao.findEntityById(id);
		Order order = orderOptional.orElseThrow(
				() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID.getErrorMessageKey()
						, id
						, ORDER_INCORRECT.getErrorCode()));
		OrderDto orderDto = modelMapper.map(order, OrderDto.class);
		orderDto.setGiftCertificates(addGiftCertificatesDto(order));
		return orderDto;
	}
}
