package com.paymybuddy.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.domain.dtos.UserCreate;
import com.paymybuddy.service.UserService;

@RestController
@RequestMapping("/account")
public class UserController {
	
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping("/register")
	public void createUser(@RequestBody UserCreate user) {
		service.createUser(user);
	}

	
}
