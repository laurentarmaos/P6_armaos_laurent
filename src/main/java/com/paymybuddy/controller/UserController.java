package com.paymybuddy.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paymybuddy.entities.User;
import com.paymybuddy.service.UserService;

@Controller
public class UserController {
	
	
	private final UserService service;
    
	public UserController(UserService service) {
		this.service = service;
	}
	
	
	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
        
		// Redirect when already logged
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "register";
        }
 
        return "redirect:/";
    }
	
	
	
	@PostMapping("/register")
	public @ResponseBody void createUser(@Valid @ModelAttribute("user") User user) {

		service.createUser(user);
	}

	
	@GetMapping("/login")
    public String login() {
    	
    	// Redirect when already logged
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
 
        return "redirect:/";
    }
	
	
	//////////////////////////////////////
	@GetMapping("/users")
    public String users(Model model){
        List<User> users = service.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
	///////////////////////////////////////
	
	
	@GetMapping("/addcontact")
    public String showContactForm(Model model) {
		model.addAttribute("friend", new User());
        return "addcontact";
    }
	
	@PostMapping("/addcontact")
	public @ResponseBody String addContact(@ModelAttribute("friend") User friend, BindingResult result) throws Exception {
		
		if(result.hasErrors()) {
			return "/addcontact";
		}
		
		service.addContact(friend);
		return "redirect:/index";
	}
	
}
