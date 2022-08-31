package com.paymybuddy.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paymybuddy.domain.entities.User;
import com.paymybuddy.service.UserService;

@Controller
//@RequestMapping("/register")
public class UserController {
	
	@Autowired
	private UserService service;
    
	@Autowired
	private UserService userService;

	
	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
        return "register";
    }
	
	
	
	@PostMapping("/register")
	public @ResponseBody void createUser(@Valid @ModelAttribute("user") User user) {

		service.createUser(user);
	}

	
	@GetMapping("/users")
    public String users(Model model){
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
	
	
}
