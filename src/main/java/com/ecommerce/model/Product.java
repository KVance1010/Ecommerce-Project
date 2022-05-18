package com.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "product", schema = "ecommerce", uniqueConstraints =  { 
		@UniqueConstraint(name = "sku_unique", columnNames = "sku_num")})
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
	@SequenceGenerator(name = "product_generator", sequenceName = "product_sequence_name", allocationSize = 1)
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
	@CreationTimestamp                                         // this will auto populate the time stamp
	private LocalDate dateCreated;
	
	@Column(name = "product_updated", nullable = false)
	@UpdateTimestamp                                           // this will auto update the time stamp
	private LocalDate lastUpdated;
}
