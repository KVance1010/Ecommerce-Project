package com.ecommerce.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.dto.OrderResponse;
import com.ecommerce.exception.PaymentException;
import com.ecommerce.model.Order;
import com.ecommerce.model.Payment;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.PaymentRepository;

@Service
public class OrderService implements com.ecommerce.service.OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderResponse orderResponse;
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	@Override
	@Transactional()
	public OrderResponse placeOrder(OrderRequest orderRequest) {
	
		Order order = orderRequest.getOrder();
		order.setStatus("IN PROGRESS");
		order.setOrderTrackingNumber(UUID.randomUUID().toString());
		orderRepository.save(order);
		
		Payment payment = orderRequest.getPayment();
		
		if (!payment.getPaymentType().equals("debit")) {
			throw new PaymentException ("Payment card type not supported");
		}
		
		payment.setOrderId(order.getId());
		paymentRepository.save(payment);
		
		
		orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
		orderResponse.setStatus("Complete");
		return orderResponse;
		
	}

}
