package com.paymybuddy.controller;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paymybuddy.entities.Transaction;
import com.paymybuddy.entities.User;
import com.paymybuddy.repositories.UserRepository;
import com.paymybuddy.service.PaymentService;
import com.paymybuddy.service.UserService;

@Controller
public class PaymentController {

	private final PaymentService service;
	private final UserService userService;
	private final UserRepository userRepo;
	
	public PaymentController(PaymentService service, UserService userService,UserRepository userRepo) {
		this.service = service;
		this.userService = userService;
		this.userRepo = userRepo;
	}
	


	@GetMapping("/accounts")
	public String showAccountForm(Model model) {
		model.addAttribute("updateUserAccount", new User());
		return "accounts";
	}
	
	@PostMapping("/accounts")
	public String addAmountFromAccount(@ModelAttribute("updateUserAccount") User dto) {
		service.addAmountFromAccount(dto);
		return "accounts";
	}

	
	
	@GetMapping("/toaccounts")
	public String showToAccountForm(Model model) {
		model.addAttribute("updateUserAccount", new User());
		return "toaccounts";
	}
	
	@PostMapping("/toaccounts")
	public String addAmountToAccount(@ModelAttribute("updateUserAccount") User dto, Model model) throws Exception{
		
		try {
			service.addAmountToAccount(dto);
			return "toaccounts";
		} catch (Exception e) {
			model.addAttribute("error","not enough amount on your user account to proceed !");
			return "toaccounts";
		}
	}
	
	
	
	@GetMapping("/transactions/{pageNo}")
	public String showTransactionForm(@PathVariable (value = "pageNo") int pageNo, Model model) {
		
		int pageSize = 5;
		
		List<User> contacts = userService.findAllFriends();

		Page<Transaction> page = service.findAllTransactions(pageNo, pageSize);
		List<Transaction> transactions = page.getContent();
		
		model.addAttribute("contacts", contacts);
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("transacs", transactions);
		
		model.addAttribute("transaction", new Transaction());
		model.addAttribute("friend", new User());
		
		return "transactions";
	}
	
	
	@PostMapping("/transactions/{pageNo}")
	public String payContact(@PathVariable (value = "pageNo") int pageNo, @ModelAttribute("friend") User friend, @ModelAttribute("transaction") Transaction transaction, Model model) throws Exception {
		
		if(!userRepo.existsByEmail(friend.getEmail())) {
			model.addAttribute("errorNotFound", "user doesn't exist !");
			return showTransactionForm(pageNo, model); 
		}
		
		if(transaction.getDescription().isBlank()) {
			model.addAttribute("errorDesc", "description is mandatory !");
			return showTransactionForm(pageNo, model);
		}
		
			
		try {
			service.payContact(friend, transaction);
			return showTransactionForm(pageNo, model);
			
		} catch (Exception e) {
			model.addAttribute("error","not enough amount on your user account to proceed or invalid value!");
			return showTransactionForm(pageNo, model);
		}
		
		
		
	}

}
