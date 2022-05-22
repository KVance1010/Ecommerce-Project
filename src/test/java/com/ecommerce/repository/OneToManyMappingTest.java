package com.ecommerce.repository;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.model.Address;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;

@SpringBootTest
public class OneToManyMappingTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productionRepository;

	@Test // save order along with the order items
	void saveOrderMetod() {

		Order order = new Order();
		order.setStatus("In progress");
		order.setOrderTrackingNumber("sdkfjal");

		// create order item 1
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setProduct(productionRepository.findById(1L).get());
		orderItem1.setQuantity(2);
		orderItem1.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(orderItem1.getQuantity())));
		orderItem1.setImageUrl("image1.png");
		order.getOrderItems().add(orderItem1);

		// create order item 2
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setProduct(productionRepository.findById(2L).get());
		orderItem2.setQuantity(3);
		orderItem2.setPrice(orderItem2.getProduct().getPrice().multiply(new BigDecimal(orderItem2.getQuantity())));
		orderItem2.setImageUrl("image2.png");
		order.getOrderItems().add(orderItem2);
		order.setTotalPrice(order.getTotalAmoung());
		
		Address address = new Address();
        address.setCity("Pune");
        address.setStreet("Kothrud");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setZipCode("411047");
		
        order.setBillingAddress(address);
		order.setTotalQuantity(2);
		
		orderRepository.save(order);
	}

	@Test
	void fetchOrderMethod() {
		Order order = orderRepository.findById(1L).get();
		System.out.println(order.getStatus());
		System.out.println(order.toString());
		for (OrderItem item: order.getOrderItems()) {
			System.out.println(item.getProduct().getProductName());
		}
	}
	
	@Test
	void deleteOrderMethod() {
		orderRepository.deleteById(1L);
	}
}
