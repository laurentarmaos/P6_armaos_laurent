package com.paymybuddy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paymybuddy.domain.dtos.UserCreate;
import com.paymybuddy.service.UserService;

@Controller
@RequestMapping("/register")
public class UserController {
	
	@Autowired
	private UserService service;

	
	@ModelAttribute("user")
	public UserCreate userCreate () {
		return new UserCreate();
	}
	
	
	@GetMapping
    public String showRegistrationForm(Model model) {
        return "register";
    }
	
	
	
	@PostMapping
	public @ResponseBody void createUser(@ModelAttribute("user") UserCreate user) {

		service.createUser(user);
	}

	
}
