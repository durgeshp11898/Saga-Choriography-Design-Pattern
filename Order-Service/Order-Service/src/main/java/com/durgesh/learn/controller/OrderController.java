package com.durgesh.learn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.learn.dto.OrderRequestDto;
import com.durgesh.learn.entity.PurchaseOrder;
import com.durgesh.learn.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
		logger.info("Received createOrder request for userId: {}, productId: {}, amount: {}", 
				orderRequestDto.getUserId(), orderRequestDto.getProductId(), orderRequestDto.getAmount());

		// Validation check for userId
		if (orderRequestDto.getUserId() == null) {
			logger.error("createOrder failed: userId is null");
			return new ResponseEntity<>("UserId cannot be null", HttpStatus.BAD_REQUEST);
		}

		// Create the purchase order
		PurchaseOrder purchaseOrder = null;
		try {
			purchaseOrder = orderService.createOrder(orderRequestDto);
			logger.info("Order created successfully for userId: {}", orderRequestDto.getUserId());
			return new ResponseEntity<>(purchaseOrder, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error creating order for userId: {}, Error: {}", orderRequestDto.getUserId(), e.getMessage());
			return new ResponseEntity<>("Error creating order", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@GetMapping
	public List<PurchaseOrder> getOrders(){
		return orderService.getAllOrders();
	}


}
