package com.ecommerce.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.model.Product;

@SpringBootTest
public class JPAQueriesTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	void findByNameOrDescriptionJPQLIndexParamMethod() {
		Product product = productRepository.findByProductNameOrProductDescriptionJPQLIndexParam(
				"product 1", "product 1 Description");
		
		System.out.println(product.getId());
		System.out.println(product.getProductName());
	}
	
	@Test
	void findByNameOrDescriptionJPQLNameParamMethod() {
		Product product = productRepository.findByNameOrDescriptionJPQLNamedParam(
				"product 2", "product 2 Description");
		
		System.out.println(product.getId());
		System.out.println(product.getProductName());
	}
	
}
