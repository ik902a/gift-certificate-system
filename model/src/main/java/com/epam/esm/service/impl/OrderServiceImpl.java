package com.epam.esm.service.impl;

import static com.epam.esm.dao.util.ParamName.LIMIT;
import static com.epam.esm.dao.util.ParamName.OFFSET;
import static com.epam.esm.exception.ErrorCode.*;
import static com.epam.esm.exception.ErrorMessageKey.*;

import java.math.BigDecimal;
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
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.PageDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateOrder;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ResourceNotExistException;
import com.epam.esm.service.OrderService;

/**
 * The {@code OrderServiceImpl} class is responsible for operations with order
 * 
 * @author Ihar Klepcha
 * @see OrderService
 */
@Service
public class OrderServiceImpl implements OrderService {
	public static Logger log = LogManager.getLogger();
	private OrderDao orderDao;
	private GiftCertificateDao giftCertificateDao;
	private ModelMapper modelMapper;

	/**
	 * Constructs service for order
	 * 
	 * @param orderDao           {@link OrderDao} DAO for order
	 * @param giftCertificateDao {@link GiftCertificateDao} DAO for certificate
	 * @param ModelMapper        {@link ModelMapper} performs object mapping
	 */
	@Autowired
	public OrderServiceImpl(OrderDao orderDao, GiftCertificateDao giftCertificateDao, ModelMapper modelMapper) {
		super();
		this.orderDao = orderDao;
		this.giftCertificateDao = giftCertificateDao;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional
	public OrderDto create(long userId, Map<String, Integer> giftCertificateMap) {
		log.info("Creating Order by data: {}", giftCertificateMap);
		Order order = buildOrder(userId, giftCertificateMap);
		order = orderDao.create(order);
		return modelMapper.map(order, OrderDto.class);
	}

	private Order buildOrder(Long userId, Map<String, Integer> giftCertificateMap) {
		Order order = new Order();
		User user = new User();
		user.setId(userId);
		order.setUser(user);
		BigDecimal cost = BigDecimal.ZERO;
		for (Map.Entry<String, Integer> giftCertificateEntry : giftCertificateMap.entrySet()) {
			String giftCertificateName = giftCertificateEntry.getKey();
			Integer quantity = giftCertificateEntry.getValue();
			GiftCertificate giftCertificate = giftCertificateDao.findEntityByName(giftCertificateName)
					.orElseThrow(() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_NAME, 
							giftCertificateName,
							GIFT_CERTIFICATE_INCORRECT));
			order.addGiftCertificateOrder(new GiftCertificateOrder(order, giftCertificate, quantity));
			cost = cost.add(giftCertificate.getPrice().multiply(new BigDecimal(quantity)));
		}
		order.setCost(cost);
		return order;
	}

	@Override
	@Transactional
	public OrderDto findById(long id) {
		log.info("Finding Order by id={}", id);
		Order order = orderDao.findEntityById(id)
				.orElseThrow(() -> new ResourceNotExistException(RESOURCE_NOT_FOUND_BY_ID, 
						String.valueOf(id), 
						ORDER_INCORRECT));
		return modelMapper.map(order, OrderDto.class);
	}

	@Override
	@Transactional
	public PageDto<OrderDto> findOrdersByUser(long userId, Map<String, String> params) {
		log.info("Finding Order by user(id={})", userId);
		User user = new User();
		user.setId(userId);
		List<Order> orderList = orderDao.findOrdersByUser(user, params);
		List<OrderDto> orderDtoList = orderList.stream()
				.map(order -> modelMapper.map(order, OrderDto.class))
				.collect(Collectors.toList());
		return buildPage(orderDtoList, user, params);
	}

	private PageDto<OrderDto> buildPage(List<OrderDto> orderDtoList, User user, Map<String, String> params) {
		int offset = Integer.parseInt(params.get(OFFSET));
		int limit = Integer.parseInt(params.get(LIMIT));
		long totalPositions = orderDao.getTotalNumberByUser(user, params);
		long totalPages = (long) Math.ceil((double) totalPositions / limit);
		long pageNumber = offset / limit + 1;
		return new PageDto<>(orderDtoList, totalPages, pageNumber, offset, limit);
	}
}
