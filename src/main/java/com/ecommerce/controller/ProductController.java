package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	private ProductService productService;
	
	@Autowired  // autowire is not needed because there is only one constructor... constructor injection
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/search")
	public ResponseEntity <List<Product>> searchProducts(@RequestParam String query){
		return ResponseEntity.ok(productService.searchProducts(query));
	}
	
	public Product createProduct(Product product) {
		return productService.createProduct(product);
	}
	
}
