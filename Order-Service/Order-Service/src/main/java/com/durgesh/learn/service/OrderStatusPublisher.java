package com.durgesh.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durgesh.learn.dto.OrderRequestDto;
import com.durgesh.learn.enumm.OrderStatus;
import com.durgesh.learn.event.OrderEvent;

import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

	@Autowired
	private Sinks.Many<OrderEvent> orderSinks;


	public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus){
		OrderEvent orderEvent=new OrderEvent(orderRequestDto,orderStatus);
		orderSinks.tryEmitNext(orderEvent);
	}
}
