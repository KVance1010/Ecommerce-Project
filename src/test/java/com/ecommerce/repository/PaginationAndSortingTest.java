package com.ecommerce.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ecommerce.model.Product;

@SpringBootTest
public class PaginationAndSortingTest {

	@Autowired
	ProductRepository productReposity;
	
	@Test
	void pagination() {
		
		// page number always starts at 0. 
		int pageNo = 0;
		// how many records per page
		int pageSize = 5;
		
		// create a pageable object. make sure you are using the pagerequest from springframework.data.domain.pagerequest
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		// call findall method and pass the pageable instance
		Page<Product> page = productReposity.findAll(pageable);
		
	    List<Product> products = page.getContent();
	    
	    products.forEach((p) -> {
	    	System.out.println(p);
	    });
	    
	    // total pages
	    int totalPage = page.getTotalPages();
	    // total elements
	    long totalElements = page.getTotalElements();
	    // number of elements
	    long numElements = page.getNumberOfElements();
	    // size
	    int size = page.getSize();
	    // last
	    boolean isLast = page.isLast();
	    // first
	    boolean isFirst = page.isFirst();
	    
	    System.out.println("total page ->" + totalPage);
	    System.out.println("total elements ->" + totalElements);
	    System.out.println("number of elements ->" + numElements);
	    System.out.println("size ->" + size);
	    System.out.println("is last ->" + isLast);
	    System.out.println("is first ->" + isFirst);
	}
}
