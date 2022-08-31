package com.paymybuddy.service;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paymybuddy.domain.entities.Role;
import com.paymybuddy.domain.entities.User;

public interface UserService extends UserDetailsService{
	
	User findByEmail(String email);
	
	void createUser(User user);
	
	List<User> findAllUsers();
	
}
