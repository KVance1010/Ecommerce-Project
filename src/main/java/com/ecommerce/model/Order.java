package com.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(name = "order_total_quantity", nullable = false)
	private int totalQuantity;
	@Column(name = "order_total_price", nullable = false)
	private BigDecimal totalPrice;
	@Column(name = "order_status")
	private String status;
	@Column(name = "order_creation_date")
	@CreationTimestamp
	private LocalDate dateCreated;
	@Column(name = "order_last_update")
	@UpdateTimestamp
    private LocalDate lastUpdated;
	
	// if you just list this on one entity the reference will be unidirectional 
	@OneToOne (cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY) 
	// @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
	// if you do not want to delete the other entity when deleting from orders. 
	// if you do not use the joincolumn annotation a foreign key will be generated automatically 
	//@JoinColumn(name = "billing_address_id", referencedColumnName = "id")
	private Address billingAddress;
	
/*	
    // unidirection 
    // default fetch type for one to many is Lazy
	// use set instead of list because a set will not allow duplicates
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name= "order_id", referencedColumnName = "id")
	private Set<OrderItem> orderItems = new HashSet<>();
*/
	
	// bidirectional ... mapped by needs to be on the one side
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
	private Set<OrderItem> orderItems = new HashSet<>();
	
	
	public BigDecimal getTotalAmoung() {
		BigDecimal amount = new BigDecimal(0.00);
		for (OrderItem item : this.orderItems) {
			amount = amount.add(item.getPrice());
		}
		return amount;
	}
}
