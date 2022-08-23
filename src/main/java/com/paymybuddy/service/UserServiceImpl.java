package com.paymybuddy.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.domain.entities.Role;
import com.paymybuddy.domain.entities.User;
import com.paymybuddy.repositories.RoleRepository;
import com.paymybuddy.repositories.UserRepository;

	@Service
	public class UserServiceImpl implements UserService{
	
		private final UserRepository userRepo;
		private final PasswordEncoder encoder;
		private final RoleRepository roleRepo;
		
		public UserServiceImpl(UserRepository userRepo, PasswordEncoder encoder, RoleRepository roleRepo) {
			this.userRepo = userRepo;
			this.encoder = encoder;
			this.roleRepo = roleRepo;
		}
		
	
		@Override
		public void createUser(User dto) {
	
			User entity = new User();
			
			entity.setEmail(dto.getEmail());
			entity.setPassword(encoder.encode(dto.getPassword()));
			entity.setFirstName(dto.getFirstName());
			entity.setLastName(dto.getLastName());
			entity.setAmount(0);
			entity.setActivate(true);
			
			Role role = roleRepo.findByRoleName("USER").get();
			entity.setRoleId(role);
			
			userRepo.save(entity);
		}


		//TODO //////////////////////////////////////////////////
		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			User user = userRepo.findByEmail(email);
	        if (user == null){
	            throw new UsernameNotFoundException("Invalid username or password.");
	        }
	        
	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
		}
		///////////////////////////////////////////////////////////

		@Override
		public User findByEmail(String email) {
			return userRepo.findByEmail(email);
		}
		
		

}
