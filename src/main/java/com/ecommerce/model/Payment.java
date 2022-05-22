package com.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;
	@Column (name = "payment_type", nullable = false)
	private String paymentType;
	@Column (name = "payment_type", nullable = false)
	private String cardNumber;
	@Column (name = "card_holder_name", nullable = false)
	private String cardHolderName;
	@Column (name = "expiration_date", nullable = false)
	private int expirationDate;
	@Column (name = "cvc", nullable = false)
	private int cvc;
	@Column (name = "order_id", nullable = false)
	private Long orderId;
	
}
