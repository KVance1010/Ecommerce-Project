package com.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Data
@Table(
		name = "users",
		uniqueConstraints = 
				@UniqueConstraint(name = "unique_email", columnNames = "email")
		)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column (name = "first_name")
	private String firstName;
	@Column (name = "last_name")
	private String lastName;
	private String email;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	           joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
	           inverseJoinColumns = @JoinColumn( name = "role_id", referencedColumnName = "roleId")
			)
	private Set<Role> roles = new HashSet<Role>();
	
}
