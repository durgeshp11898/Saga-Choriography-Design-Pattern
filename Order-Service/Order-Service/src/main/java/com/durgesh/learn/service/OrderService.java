package com.durgesh.learn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durgesh.learn.dto.OrderRequestDto;
import com.durgesh.learn.entity.PurchaseOrder;
import com.durgesh.learn.enumm.OrderStatus;
import com.durgesh.learn.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderStatusPublisher orderStatusPublisher;

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


	@Transactional
	public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {

		logger.info("createOrder: "+orderRequestDto.toString());
		PurchaseOrder order = orderRepository.save(convertDtoToEntity(orderRequestDto));
		orderRequestDto.setOrderId(order.getId());
		logger.info("createOrder: "+orderRequestDto.getOrderId());

		//produce kafka event with status ORDER_CREATED
		logger.info("produce kafka event with status ORDER_CREATED: ");
		orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);

		logger.info("Order: "+order.toString());
		return order;
	}

	public List<PurchaseOrder> getAllOrders(){
		logger.info("Service Start for getAllOrders: ");

		return orderRepository.findAll();
	}


	private PurchaseOrder convertDtoToEntity(OrderRequestDto dto) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setProductId(dto.getProductId());
		purchaseOrder.setUserId(dto.getUserId());
		purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
		purchaseOrder.setPrice(dto.getAmount());
		return purchaseOrder;
	}

}
