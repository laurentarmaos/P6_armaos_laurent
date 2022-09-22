package com.paymybuddy.service;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paymybuddy.entities.Role;
import com.paymybuddy.entities.User;

public interface UserService extends UserDetailsService{
	
	void createUser(User user);
	
	List<User> findAllUsers();
	
	void addContact(User dto) throws Exception;
	
	List<User> findAllFriends();

	User findConnectedUser();
}
