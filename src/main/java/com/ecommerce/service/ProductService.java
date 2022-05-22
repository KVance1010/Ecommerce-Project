package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Product;

public interface ProductService {

	List<Product> searchProducts(String query);

	Product createProduct(Product product);

}
