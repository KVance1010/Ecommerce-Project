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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	@Column(name= "role_name")
	private String roleName;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "roles") // mapped by is the Set that was named in user
	// can also use this instead of mappedby
//	@JoinTable(name = "users_roles", 
//	           joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"),
//	           inverseJoinColumns = @JoinColumn( name = "user_id", referencedColumnName = "userId")
//			)
	private Set <User> users = new HashSet<>();

}
