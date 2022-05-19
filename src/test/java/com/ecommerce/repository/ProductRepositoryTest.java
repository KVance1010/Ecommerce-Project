package com.ecommerce.repository;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.model.Product;

@SpringBootTest
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;


	@Test
	void saveMethod() {
		// create product
		Product product = new Product();
		product.setProductName("product 1");
		product.setProductDescription("product 1 description");
		product.setSku("15d1d");
		product.setPrice(new BigDecimal(441));
		product.setActive(true);
		product.setImageUrl("product.png");

		// save product
		Product savedObject = productRepository.save(product);
		// display product
		System.out.println(savedObject.getId());
		System.out.println(savedObject.toString());
	}

	@Test
	void findByIdMethod() {
		Long id = 1L;
		Product product = productRepository.findById(id).get();
		System.out.println(product);
	}

	@Test
	void updateUsingSaveMethod() {

		// find or retrieve an entity by id
		Long id = 1l;
		Product product = productRepository.findById(id).get();

		// update entity information
		product.setProductName("updated product 1");
		product.setProductDescription("updated product 1 desc");

		// save the updated entity
		productRepository.save(product);

	}

	@Test
	void saveAllMethod() {
		// create product
		Product product = new Product();
		product.setProductName("product 2");
		product.setProductDescription("product 2 description");
		product.setSku("100abcd");
		product.setPrice(new BigDecimal(200));
		product.setActive(true);
		product.setImageUrl("product2.png");

		Product product2 = new Product();
		product2.setProductName("product 3");
		product2.setProductDescription("product 3 description");
		product2.setSku("100abcde");
		product2.setPrice(new BigDecimal(300));
		product2.setActive(true);
		product2.setImageUrl("product3.png");

		productRepository.saveAll(List.of(product, product2));
	}
	
	@Test
	void findAllMethod() {
		List <Product> products = productRepository.findAll();
		products.forEach((p)-> {
			System.out.println(p.getProductName());
		});
	}
	
	@Test
	void deleteByIdMethod() {
		Long id = 3L;
		productRepository.deleteById(id);
		
	}
	
	@Test
	void deleteMethod() {
		// find an entity by id
		Long id = 2L;
		Product product = productRepository.findById(id).get();
		//delete the entity
		productRepository.delete(product);
	}
	
	
	@Test
	void deleteAllMethod() {
		productRepository.deleteAll();
	}
	
	@Test
	void deleteAllByIdMethod() {
		// find an entity by id
		Long id1 = 11L;
		Long id2 = 12L;
		
		Product product = productRepository.findById(id1).get();
		Product product1 = productRepository.findById(id2).get();
		//delete the entity
		productRepository.deleteAll(List.of(product,product1));
	}
	
	@Test
	void countMethod() {
		Long count = productRepository.count();
		System.out.println(count);
	}
	
	@Test
	void existByIdMethod() {
		Long id = 1L;
		boolean results = productRepository.existsById(id);
		System.out.println(results);
	}
	

}
