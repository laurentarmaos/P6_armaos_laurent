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
	public String showAccountForm(Model model) {
		User user = userService.findConnectedUser();
		model.addAttribute("user", user);
		return "accounts";
	}
	
	@PostMapping("/accounts")
	public @ResponseBody void addAmountFromAccount(@ModelAttribute("user") User user, double amount) {
		service.addAmountFromAccount(amount);
	}

	
	
	
	
	// show all the previous transactions from connected user/////////////////////////////////////////////////////
	@GetMapping("/transactions")
	public String transactions(Model model) {
		List<Transaction> transactions = service.findAllTransactions();
		model.addAttribute("transactions", transactions);
		return "transactions";
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	// show form to transfer money from connected user to a contact//////////////////////////////////////////////
//	@GetMapping("/transactions")
//	public String showTransactionForm(Model model) {
//		model.addAttribute("transaction", new Transaction());
//		model.addAttribute("friend", new User());
//		return "transactions";
//	}
//	
//	@PostMapping("/transactions")
//	public @ResponseBody void payContact(@ModelAttribute("friend") User friend, @ModelAttribute("transaction") Transaction transaction) {
//		service.payContact(friend, transaction);
//	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////

}
