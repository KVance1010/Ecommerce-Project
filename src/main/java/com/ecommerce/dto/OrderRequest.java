package com.ecommerce.dto;

import com.ecommerce.model.Order;
import com.ecommerce.model.Payment;

import lombok.Data;

@Data
public class OrderRequest {

	private Order order;
	private Payment payment;
	
}
