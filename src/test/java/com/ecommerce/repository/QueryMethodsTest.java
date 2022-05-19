package com.ecommerce.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.model.Product;

@SpringBootTest
public class QueryMethodsTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	void findByNameMethod() {
		Product product = productRepository.findByProductName("product 2");
		System.out.println("findByName");
		System.out.println(product.getId());
		System.out.println(product.getProductName());
		System.out.println(product.getProductDescription());
	}
	
	@Test
	void findByIdMethod() {
		Product product = productRepository.findById(1l).get();
		System.out.println("findByID");
		System.out.println(product.getId());
		System.out.println(product.getProductName());
		System.out.println(product.getProductDescription());
	}
	
	@Test
	void findByNameOrDescriptionMethod() {
		List<Product> product = productRepository.findByProductNameOrProductDescription( "product1", "product 1 description");
	    System.out.println("or");
		product.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getProductName());
		});
	}
	
	@Test
	void findByNameAndDescriptionMethod() {
		List<Product> product = productRepository.findByProductNameAndProductDescription( "product1", "product 1 description");
	    System.out.println("and");
		product.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getProductName());
		});    
	}
	
	@Test
	void findDiistinctByPruductMethod() {
		Product product = productRepository.findDistinctByProductName("product 1");
		System.out.println("distinct");
		System.out.println(product.getId());
		System.out.println(product.getProductName());
		System.out.println(product.getProductDescription());
	}
	
	@Test
	void findByPriceGreaterThanMethod() {
		List<Product> product = productRepository.findByPriceGreaterThan(new BigDecimal(100));
		System.out.println("price greater");
		product.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getProductName());
		});    
	}
	
	@Test
	void findByLessThanMethod() {
		List<Product> product = productRepository.findByPriceLessThan(new BigDecimal(200));
		System.out.println("price less");
		product.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getProductName());
		});    
	}
	
	@Test
	void findByProductNameContainingMethod() {
		List<Product> product = productRepository.findByProductNameContaining("product 3");
		System.out.println("Containing");
		product.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getProductName());
		});    
	}
	
	@Test
	void findByProductNameLikeMethod() {
		List<Product> product = productRepository.findByProductNameLike("product 3");
		System.out.println("Like");
		product.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getProductName());
		});    
	}
	
	@Test
	void findByPriceBetweenMethod() {
		List<Product> product = productRepository.findByPriceBetween(new BigDecimal (150), new BigDecimal (250) );
		System.out.println("between");
		product.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getProductName());
		});    
	}
	@Test
	void findByDateCreatedBetweenMethod() {
		LocalDate startDate = LocalDate.of(2022, 05, 17);
	    LocalDate endDate = LocalDate.of(2022, 05, 17);	
		List<Product> product = productRepository.findByDateCreatedBetween(startDate , endDate);
		System.out.println("date created");
		product.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getProductName());
		});    
	}

	@Test
	void findByDateUpdatedBetweenMethod() {
		LocalDate startDate = LocalDate.of(2022, 05, 17);
	    LocalDate endDate = LocalDate.of(2022, 05, 17);	
		List<Product> product = productRepository.findByLastUpdatedBetween(startDate , endDate);
		System.out.println("date updated");
		product.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getProductName());
		});    
	}
	
	@Test
	void findByProductNameInMethod() {
		List <Product> products = productRepository.findByProductNameIn(List.of("product 1", "product 2", "product 3"));
		System.out.println("list of products");
		products.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getProductName());
		});    
	}
	
	@Test
	void findByFirst2OrderByProductNameMethod() {
		List<Product> products = productRepository.findFirst2ByOrderByProductNameAsc();
		System.out.println("asc 2 prod");
		products.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getProductName());
		});    
	}
	
}
