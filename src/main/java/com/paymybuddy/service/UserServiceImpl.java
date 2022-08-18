package com.paymybuddy.service;


import org.springframework.stereotype.Service;

import com.paymybuddy.domain.dtos.UserCreate;
import com.paymybuddy.domain.entities.User;
import com.paymybuddy.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepo;
	
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	

	@Override
	public void createUser(UserCreate dto) {

		User entity = new User();
		
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setAmount(0);
		
		userRepo.save(entity);
	}


}
