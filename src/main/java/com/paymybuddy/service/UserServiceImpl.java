package com.paymybuddy.service;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.domain.entities.Role;
import com.paymybuddy.domain.entities.User;
import com.paymybuddy.repositories.RoleRepository;
import com.paymybuddy.repositories.UserRepository;
import com.paymybuddy.security.EncoderConfig;



	@Service
	public class UserServiceImpl implements UserService{
		
		@Autowired
	    private EncoderConfig encode;
	
		private final UserRepository userRepo;
		private final RoleRepository roleRepo;
		
		public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo) {
			this.userRepo = userRepo;
			this.roleRepo = roleRepo;
		}
		
	
		@Override
		public void createUser(User dto) {
	
			User entity = new User();
			
			entity.setEmail(dto.getEmail());
			entity.setPassword(encode.passwordEncoder().encode(dto.getPassword()));
			entity.setFirstName(dto.getFirstName());
			entity.setLastName(dto.getLastName());
			entity.setAmount(0);
			entity.setActivate(true);
			
			Role role = roleRepo.findByRoleName("USER");
			entity.setRoles(Arrays.asList(role));
			
			userRepo.save(entity);
		}


	
		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			User user = userRepo.findByEmail(email);
	        if (user == null){
	            throw new UsernameNotFoundException("Invalid username or pwd.");
	        }
	        
	        
	        return new org.springframework.security.core.userdetails.User(
	        		user.getEmail(), 
	        		user.getPassword(), 
	        		user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList())
	        		);
		}
		
	
		

		@Override
		public User findByEmail(String email) {
			return userRepo.findByEmail(email);
		}
		
		
		@Override
	    public List<User> findAllUsers() {
	        List<User> users = userRepo.findAll();
	        return users.stream()
	                .map((user) -> mapToUsers(user))
	                .collect(Collectors.toList());
	    }
		
		
		private User mapToUsers(User user){
	        User users = new User();
	        users.setFirstName(user.getFirstName());
	        users.setLastName(user.getLastName());
	        users.setEmail(user.getEmail());
	        return users;
	    }

}
