package com.paymybuddy.service;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
		
		
		private final EncoderConfig encode;
		private final UserRepository userRepo;
		private final RoleRepository roleRepo;
		
		public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, EncoderConfig encode) {
			this.userRepo = userRepo;
			this.roleRepo = roleRepo;
			this.encode = encode;
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

		
		public String userInfos() {
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	String userInfoEmail = authentication.getName();
	    	
	    	return userInfoEmail;
	    }
		
		@Override
		public void addContact(User dto){
			
			User user = new User();
			String userMail = userInfos();
			
			user = userRepo.findByEmail(userMail);
			
			
			User contact = new User();
			String contactMail = dto.getEmail();
			
			contact = userRepo.findByEmail(contactMail);
			
			//TODO gérer erreur user = contact
		
			user.getFriends().add(contact);
			
			userRepo.save(user);
		}
		
	
		
}
