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
	
	/**
	 * Returns the found product entry by using its name as a search criteria
	 * If no product entry is found, this method returns null.
	 * @param name
	 * @return 
	 */
	public Product findByProductName(String name); // you do not have to list public on this method because interfaces are always public
	
	/**
	 * Returns an optional which contains the found product
	 * entry by using its id as search criteria. if no product entry
	 * is found, this method returns an empty Optional.
	 * @param id
	 * @return 
	 */
	public Optional<Product> findById(Long id);
	
	/**
	 * Returns the found list of product entries whose title or description is given 
	 * as a method parameter. If no product entries is found, this method
	 * returns an empty list
	 * @param name and description
	 * @return
	 */
	public List<Product> findByProductNameOrProductDescription(String name, String Description);
	
	/**
	 * Returns the found list of product entries whose title or description is given 
	 * as a method parameter. If no product entries is found, this method
	 * returns an empty list
	 * @param name and description
	 * @return 
	 */
	public List<Product> findByProductNameAndProductDescription(String name, String Description);
	
	/**
	 * Returns the distinct product entry whose name is given as a method parameter 
	 * If no product entry is found, this method returns null.
	 * @param name
	 * @return 
	 */
	public Product findDistinctByProductName(String name);
	
	/**
	 * Returns the product whose price is greater than given price as method parameter
	 * @param price
	 * @return 
	 */
	public  List<Product>  findByPriceGreaterThan(BigDecimal price);
	
	/**
	 * Returns the product whose price is less than given price as method parameter
	 * @param price
	 * @return 
	 */
	List<Product> findByPriceLessThan(BigDecimal price);
	
	/**
	 * Returns filtered the product records that match the given text
	 * @param name
	 * @return 
	 */
	List<Product> findByProductNameContaining(String name);
	
	/**
	 * Returns products based on SQL like condition
	 * @param name
	 * @return 
	 */
	List<Product> findByProductNameLike(String name);
	
	/**
	 * Returns the the product between the two prices
	 * @param startPrice
	 * @param endPrice
	 * @return 
	 */
	List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);
	
	/**
	 * Returns the the product between the two Dates
	 * @param startDate
	 * @param endDate
	 * @return 
	 */
	List<Product> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate);
	
	/**
	 * Returns the the product that was updated between the two Dates
	 * @param startDate
	 * @param endDate
	 * @return 
	 */
	List<Product> findByLastUpdatedBetween(LocalDate startDate, LocalDate endDate);
	
	/**
	 * Returns list of products based on multiple values
	 * @param name
	 * @return 
	 */
	List<Product> findByProductNameIn(List<String> names);
	
	/**
	 * Returns list of products based first two order by product name in ascending order
	 * @param name
	 * @return 
	 */
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
