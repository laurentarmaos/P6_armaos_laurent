package com.paymybuddy.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paymybuddy.entities.Transaction;
import com.paymybuddy.entities.User;
import com.paymybuddy.service.PaymentService;
import com.paymybuddy.service.UserService;

@Controller
public class PaymentController {

	private final PaymentService service;
	private final UserService userService;
	
	public PaymentController(PaymentService service, UserService userService) {
		this.service = service;
		this.userService = userService;
	}
	


	@GetMapping("/accounts")
	public String showAccountForm() {
		return "accounts";
	}
	
	@PostMapping("/accounts")
	public @ResponseBody void addAmountFromAccount(@ModelAttribute("amount") double amount) {
		service.addAmountFromAccount(amount);
	}

	
	
//	@GetMapping("/transactions")
//	public String transactions(Model model) {
//		List<Transaction> transactions = service.findAllTransactions();
//		model.addAttribute("transactions", transactions);
//		return "transactions";
//	}
//	
	
	
//	@GetMapping("/transactions")
//	public String contacts(Model model) {
//		List<User> contacts = userService.findAllFriends();
//		model.addAttribute("contacts", contacts);
//		return "transactions";
//	}
//	
	
	
	@GetMapping("/transactions")
	public String showTransactionForm(Model model) {
		model.addAttribute("transaction", new Transaction());
		model.addAttribute("friend", new User());
		
		List<User> contacts = userService.findAllFriends();
		model.addAttribute("contacts", contacts);
		
		return "transactions";
	}
	
	
	
	@PostMapping("/transactions")
	public @ResponseBody String payContact(@ModelAttribute("friend") User friend, @ModelAttribute("transaction") Transaction transaction, Model model) {
		try {
			service.payContact(friend, transaction);
			return "transactions";
			
		} catch (Exception e) {
			model.addAttribute("not enough amount on your account to proceed !");
			return "transactions";
		}
		
	}

}
