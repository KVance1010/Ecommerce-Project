package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	// constructor injection... we do not have to do autowire because there is only one constructor.
	public ProductServiceImpl(ProductRepository productRepoository) {
		this.productRepository = productRepoository;
	}
	@Override
	public List<Product> searchProducts(String query) {
		List<Product> products =productRepository.searchProducts(query);
		return products;
	}
	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

}
