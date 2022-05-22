package com.ecommerce.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.model.Role;
import com.ecommerce.model.User;

@SpringBootTest
public class ManyToManyUnidirectionalTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void saveUser() {
		User user = new User();
		user.setFirstName("Kyle");
		user.setLastName("Vance");
		user.setEmail("kvance1010@gmail.com");
		user.setPassword("secrete");
		
		Role admin = new Role();
		admin.setRoleName("Role_Admin");
		
		Role customer = new Role();
		customer.setRoleName("Customer");
		
		user.getRoles().add(customer);
		user.getRoles().add(admin);
		
		userRepository.save(user);
	}
	
	@Test
	void updateUser() {
		User user = userRepository.findById(1L).get();
		user.setFirstName("John");
		user.setEmail("ron@john.com");
		
		Role roleUser = new Role();
		roleUser.setRoleName("role_user");
		
		user.getRoles().add(roleUser);
	}
	
	@Test
	void fetchUser() {
		User user = userRepository.findById(1L).get();
		System.out.println(user);
		user.getRoles().forEach((r) -> {
			System.out.println(r.getRoleName());
		});
		
	}
	
	@Test
	void deleteUser() {
		userRepository.deleteById(1L);
	
	}
}
