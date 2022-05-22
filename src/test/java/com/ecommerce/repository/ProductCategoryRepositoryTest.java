package com.ecommerce.repository;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductCategory;

@SpringBootTest
public class ProductCategoryRepositoryTest {
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Test
	void saveProductCategory() {
		
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("books");
		productCategory.setCategoryDescription("books description");
		
		Product product1 = new Product();
		
		product1.setProductName("core java");
		product1.setPrice(new BigDecimal(1000));
		product1.setImageUrl("image1.png");
		product1.setSku("abc");
		product1.setActive(true);

		product1.setCategory(productCategory);
		productCategory.getProducts().add(product1);
		
			
		Product product2 = new Product();
		
		product2.setProductName("effective java");
		product2.setPrice(new BigDecimal(3000));
		product2.setImageUrl("image2.png");
		product2.setSku("abce");
		product2.setActive(true);

		product2.setCategory(productCategory);
		productCategory.getProducts().add(product2);
		
		productCategoryRepository.save(productCategory);
	}

}
