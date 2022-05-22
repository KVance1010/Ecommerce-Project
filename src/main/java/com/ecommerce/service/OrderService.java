package com.ecommerce.service;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.dto.OrderResponse;

public interface OrderService {

	OrderResponse placeOrder(OrderRequest orderRequest);
}
