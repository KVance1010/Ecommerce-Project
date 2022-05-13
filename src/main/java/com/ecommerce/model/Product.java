package com.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table (
		name = "product", 
		schema = "ecommerce",
		uniqueConstraints =  {
				@UniqueConstraint(name = "sku_unique", columnNames = "sku_num")

		}
)
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@Column(name = "sku_num",nullable = false)
	private String sku;
	@Column(name = "product_name", nullable = false)
	private String productName;
	@Column(name = "product_description")
	private String productDescription;
	@Column(name = "product_price")
	private BigDecimal price;
	@Column(name = "currently_active")
	private boolean active;
	@Column(name = "image_url")
	private String imageUrl;
	@Column(name = "product_created", nullable = false)
	private LocalDateTime dateCreated;
	@Column(name = "product_updated", nullable = false)
	private LocalDateTime lastUpdated;

	public Product() {
		super();
	}

	public Product(String sku, String productName, String productDescription, BigDecimal price, boolean active,
			String imageUrl, LocalDateTime dateCreated, LocalDateTime lastUpdated) {
		super();
		this.sku = sku;
		this.productName = productName;
		this.productDescription = productDescription;
		this.price = price;
		this.active = active;
		this.imageUrl = imageUrl;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}
}
