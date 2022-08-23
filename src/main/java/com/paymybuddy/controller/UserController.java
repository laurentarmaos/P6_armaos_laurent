package com.paymybuddy.controller;


import javax.validation.Valid;

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
import com.paymybuddy.domain.entities.User;
import com.paymybuddy.service.UserService;

@Controller
//@RequestMapping("/register")
public class UserController {
	
	@Autowired
	private UserService service;


	
	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
        return "register";
    }
	
	
	
	@PostMapping("/register")
	public @ResponseBody void createUser(@Valid @ModelAttribute("user") User user) {

		service.createUser(user);
	}

	
	
}
