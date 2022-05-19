package com.ecommerce.repository;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.model.Product;

@SpringBootTest
public class NamedQuerieTest {

	@Autowired
	ProductRepository productReposity;
	
	@Test
	void namedJPQLQuery() {
		Product product = productReposity.findByPrice(new BigDecimal(100));
		System.out.println(product.getId());
		System.out.println(product.getProductName());
	}
	
	@Test
	void namedJPQLQueries() {
		List<Product> products = productReposity.findAllOrderByNameDesc();
		
		products.forEach((p)->{
			System.out.println(p.getProductName());
			System.out.println(p.getProductDescription());
		});
	}
	
	@Test
	void namedNativeSQLQuery() {
		Product product = productReposity.findByDescription("product 1 description");
		
		System.out.println(product.getId());
		System.out.println(product.getProductDescription());
	}
	
	@Test
	void namedNativeSQLListAll() {
		List<Product> products = productReposity.findAllOrderByNameASC();
		products.forEach((p)->{
			System.out.println(p.getProductName());
			System.out.println(p.getProductDescription());
		});
		
	}
	
	
}
