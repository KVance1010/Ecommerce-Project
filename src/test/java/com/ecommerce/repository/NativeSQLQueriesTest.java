package com.ecommerce.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.model.Product;

@SpringBootTest
public class NativeSQLQueriesTest {


	@Autowired
	private ProductRepository productRepository;
	
	@Test
	void findByNameOrDescriptionSQLIndexParamMethod() {
		Product product = productRepository.findByNameorDescriptionSQLIndexParam("product 1", "product 1 description");
		System.out.println(product.getId());
		System.out.println(product.getProductName());
	}
	
	@Test
	void findByNameOrDescriptionSQLNameParamMethod() {
		Product product = productRepository.findByNameorDescriptionSQLNamedParam("product 1", "product 1 description");
		System.out.println(product.getId());
		System.out.println(product.getProductName());
	}
	
	
}
