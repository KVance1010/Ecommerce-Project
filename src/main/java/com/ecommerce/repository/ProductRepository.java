package com.ecommerce.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.model.Product;

// @Repository. you do not need this annotate when using JpaRepository
// by extending JpaReposity you get access to all the crud methods.
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	/* ******* this are actually used in the app   *******/
	
	
	@Query("SELECT p FROM Product p WHERE "+
			"p.product_name LIKE CONCAT('%',:query, '%')"+
			"p.product_description LIKE CONCAT('%', :query, '%')")
	List<Product> searchProducts(String query);
	
	// use table name instead of class and have to use the star instead of p to get all the products.
	@Query(nativeQuery = true, value = "SELECT * FROM products p WHERE "+
			"p.product_name LIKE CONCAT('%',:query, '%')"+
			"p.product_description LIKE CONCAT('%', :query, '%')")
	List<Product> searchProductsSQL(String query);
	
	
	
	
	
	
	
     /* *******     These are options that can be used to query a database      ***********/	
	
		
	// this is broken down into two parts findBy (query keyword) and ProductName (variable name)
	// more  can be added to this to a specific search  find First2 By  and/or ProductNameAndId
	
	//   find ... ByVarible              //   Distinct, First, Top  // Top10, First3, ect // Top and First do the same thing
	//   read ... ByVarible 
	//   query ... ByVarible 
	//   count ... ByVarible 
	//   get ... ByVarible 

	//   findByVariable1 ... Variable2         // and, or 	
	
	//  findByVariable...                 // LessThan, GreatThan, Containing, Like, Between,  
	
	//  findByFirst2OrderByVariable...       // OrderBy, Asc, Desc
	
	
	public Product findByProductName(String name); // you do not have to list public on this method because interfaces are always public
	
	public Optional<Product> findById(Long id);

	public List<Product> findByProductNameOrProductDescription(String name, String Description);

	public List<Product> findByProductNameAndProductDescription(String name, String Description);

	public Product findDistinctByProductName(String name);
	
	public  List<Product>  findByPriceGreaterThan(BigDecimal price);

	List<Product> findByPriceLessThan(BigDecimal price);
	
	List<Product> findByProductNameContaining(String name);

	List<Product> findByProductNameLike(String name);
	
	List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);
	
	List<Product> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate);
	
	List<Product> findByLastUpdatedBetween(LocalDate startDate, LocalDate endDate);
	
	List<Product> findByProductNameIn(List<String> names);

	List<Product> findFirst2ByOrderByProductNameAsc();
	
	// define JPQL query using @query annotation with index or position parameters
	// you do not need to use the JPQL naming convention when using the @Query annotation
	// you can name the parameters with and index using ? or you can use the name of the parameter
	// if using index you need to make sure the order matched. 1 = name, 2 = description
	// you do not need to use the AS for an alias. also we are using the Product from our entity model, so it is capitalized
	@Query("SELECT p FROM Product p WHERE p.productName = ?1 or p.productDescription = ?2")
	Product findByProductNameOrProductDescriptionJPQLIndexParam(String name, String description);
	
	// define JPQL query using @Query annotation with Named parameters
	// using name parameters instead of index
	// you have to use the : when using named parameters
	@Query ("SELECT p FROM Product p WHERE p.productName = :name OR p.productDescription = :description ")
	Product findByNameOrDescriptionJPQLNamedParam (@Param("name") String name,
			                                       @Param("description") String description);
	
	
	// creating a Native SQL query with indexes... index must match the order (variable 1, variable 2, ect)	
	@Query (nativeQuery = true, value = "SELECT * FROM product AS p WHERE p.product_name ="
			+ " ?1 OR p.product_description = ?2")
	Product findByNameorDescriptionSQLIndexParam(String name, String description);
	
	// creating a Native SQL query with named parameters ... names can be in any order	
	@Query (nativeQuery = true, value = "SELECT * FROM product AS p WHERE p.product_name ="
				+ " :name OR p.product_description = :description")
	Product findByNameorDescriptionSQLNamedParam(@Param("name") String name,
                                                 @Param("description") String description);
	
	// Define Named JPQL query from product
	Product findByPrice(BigDecimal price);
	
	List<Product> findAllOrderByNameDesc();
	
	// define named native sql query you have to add @Query annotation
	@Query(nativeQuery = true)
	Product findByDescription(String description);
	
	@Query(nativeQuery = true)
	List<Product> findAllOrderByNameASC();
	
}
