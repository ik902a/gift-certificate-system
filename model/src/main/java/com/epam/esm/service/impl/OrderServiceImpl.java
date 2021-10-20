package com.epam.esm.service.impl;

import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;
import static com.epam.esm.util.ParamName.LIMIT;
import static com.epam.esm.util.ParamName.OFFSET;

import java.math.BigDecimal;
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
import com.epam.esm.dto.OrderDataDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.PageDto;
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
		addGiftCertificateOrderData(order);//TODO incorrect adding
		OrderDto orderDto = modelMapper.map(order, OrderDto.class);
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
			cost = cost.add(giftCertificate.getPrice().multiply(new BigDecimal(quantity)));
		}
		order.setCost(cost);
		return order;
	}

	private void addGiftCertificateOrderData(Order order) {//TODO incorrect adding
		order.getGiftCertificateOrderList().stream()
			.forEach(giftCertificateOrder -> { giftCertificateOrder.setId(
					new GiftCertificateOrderKey(order.getId(), giftCertificateOrder.getGiftCertificate().getId()));
			orderDao.createGiftCertificateOrder(giftCertificateOrder); });		
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
		return orderDto;
	}

	@Override
	@Transactional
	public PageDto<OrderDto> findOrdersByUser(User user, Map<String, String> params) {
		log.info("FIND User BY PARAMS Service {}", params);
		List<Order> orderList = orderDao.findOrdersByUser(user, params);
		List<OrderDto> orderDtoList = orderList.stream()
				.map(order -> modelMapper.map(order, OrderDto.class))
				.collect(Collectors.toList());
		return buildPage(orderDtoList, user, params);
	}
	
	private PageDto<OrderDto> buildPage(List<OrderDto> orderDtoList, User user, Map<String, String> params) {
		int offset = Integer.parseInt(params.get(OFFSET));
		int limit = Integer.parseInt(params.get(LIMIT));
		log.info("Service offset={}, limit={}", offset, limit);
		long totalPositions = orderDao.getTotalNumberByUser(user, params);
		long totalPages = (long) Math.ceil((double) totalPositions / limit);
		long pageNumber = offset / limit + 1;
		log.info("Service page={}", pageNumber);
      return new PageDto<>(orderDtoList, totalPages, pageNumber, offset, limit);
	}
}
