package com.ecommerce.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.model.Role;
import com.ecommerce.model.User;

@SpringBootTest
public class ManyToManyBiDirectionalTest {

	@Autowired
	RoleRepository roleRepository;
	
	@Test
	void saveRole() {
		User user = new User();
		user.setFirstName("Kyle");
		user.setLastName("Vance");
		user.setEmail("kvance1010@gmail.com");
		user.setPassword("secrete");
	
		User admin = new User();
		admin.setFirstName("john");
		admin.setLastName("mance");
		admin.setEmail("mance@gmail.com");
		admin.setPassword("secrete");
		
		Role roleAdmin = new Role();
		roleAdmin.setRoleName("Role_Admin");
		
		
		roleAdmin.getUsers().add(user);
	    roleAdmin.getUsers().add(admin);
		
		user.getRoles().add(roleAdmin);
		admin.getRoles().add(roleAdmin);
		
		roleRepository.save(roleAdmin);
	}
	
	@Test
	void fetchRole() {
		List<Role> roles= roleRepository.findAll();
		roles.forEach((r) ->{
			System.out.println(r);
			r.getUsers().forEach((u)->{
				System.out.println(u.getFirstName());
			});
		});
		
	}
	
}
