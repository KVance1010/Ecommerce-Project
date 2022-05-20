package com.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table (name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "order_tracking_number", nullable = false, unique = true)
	private String orderTrackingNumber;
	@Column(name = "total_quantity", nullable = false)
	private int totalQuantity;
	@Column(name = "total_price", nullable = false)
	private BigDecimal totalPrice;
	private String status;
	
	@CreationTimestamp
	private LocalDate dateCreated;
	@UpdateTimestamp
    private LocalDate lastUpdated;
	
	// if you just list this on one entity the reference will be unidirectional 
	@OneToOne (cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY) 
	// if you do not want to delete the other entity when deleting from orders. 
	// @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
	// if you do not use the joincolumn annotation a foreign key will be generated automatically 
	//@JoinColumn(name = "billing_address_id", referencedColumnName = "id")
	private Address billingAddress;
    
}
