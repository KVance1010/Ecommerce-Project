package com.ecommerce.repository;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.model.Address;
import com.ecommerce.model.Order;

@SpringBootTest
public class OneToOneUnidirectionalMappingTest {

	@Autowired
	OrderRepository orderRepository;
	
	@Test
	void saveOrderMethod() {
		Order order = new Order();
		order.setOrderTrackingNumber("100af");
		order.setTotalQuantity(5);
		order.setStatus("IN PROGRESS");
		order.setTotalPrice(new BigDecimal(1000));
		
		Address address = new Address();
		address.setCity("Reno");
		address.setCountry("US");
		address.setState("NV");
		address.setZipCode("89502");
		address.setStreet("Klondike");
		
		order.setBillingAddress(address);

		orderRepository.save(order);
	}
	
	@Test
	void UpdateOrderMethod() {
		
		Order order = orderRepository.findById(1L).get();
		order.setStatus("Delivered");

		orderRepository.save(order);
	}
	
	@Test
	void getOrderMethod() {
		Order order =orderRepository.findById(1L).get();
		System.out.println(order);
	}
	
	@Test
	void deleteOrderMethod() {
		orderRepository.deleteById(1L);
	}
}
