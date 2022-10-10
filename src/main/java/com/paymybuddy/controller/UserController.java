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
import com.paymybuddy.repositories.UserRepository;
import com.paymybuddy.service.UserService;

@Controller
public class UserController {
	
	
	private final UserService service;
	private final UserRepository userRepo;
    
	public UserController(UserService service, UserRepository userRepo) {
		this.service = service;
		this.userRepo = userRepo;
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
	
	
	
	@GetMapping("/contacts")
	public String contacts(Model model) {
		List<User> contacts = service.findAllFriends();
		model.addAttribute("contacts", contacts);
		return "contacts";
	}
	
	
	
	@GetMapping("/addcontact")
    public String showContactForm(Model model) {
		model.addAttribute("friend", new User());
        return "addcontact";
    }
	
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute("friend") User friend, BindingResult result, Model model) throws Exception {
		
		if(userRepo.existsByEmail(friend.getEmail())) {
			
			try {
				service.addContact(friend);
				return "redirect:/";
				
			} catch (Exception e) {
				
				model.addAttribute("error", "can't add yourself as a contact or add an already existant contact !");
				return "addcontact";
			}
			
		} else {
			model.addAttribute("errorNotFound", "user doesn't exist !");
			return "addcontact";
		}
				
		
	
		
	
		
	}
	
}
