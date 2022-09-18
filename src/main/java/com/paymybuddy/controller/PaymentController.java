package com.paymybuddy.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paymybuddy.entities.BankAccount;
import com.paymybuddy.entities.Transaction;
import com.paymybuddy.entities.User;
import com.paymybuddy.service.PaymentService;

@Controller
public class PaymentController {

	private final PaymentService service;
	
	public PaymentController(PaymentService service) {
		this.service = service;
	}
	
	
	// show form to add an account to connected user/////////////////////////////////////////////////////////////
	@GetMapping("/accounts")
	public String showAccountForm() {
		return "accounts";
	}
	
	@PostMapping("/accounts")
	public @ResponseBody void addAccount() {
		service.addAccount();
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	// show list of user accounts to choose which will be used to add money to connected user/////////////////////
	@GetMapping("/addfromaccount")
	public String showAccounts(Model model) {
		List<BankAccount> accounts = service.findAllAccounts();
		model.addAttribute("userAccounts", accounts);
		return "addfromaccount";
	}
	
	@PostMapping("/addfromaccount")
	public @ResponseBody void addAmountFromAccount(@ModelAttribute("userAccounts") BankAccount account, double amount ) {
		service.addAmountFromAccount(account, amount);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	// show all the previous transactions from connected user/////////////////////////////////////////////////////
	@GetMapping("/transactions")
	public String transactions(Model model) {
		List<Transaction> transactions = service.findAllTransactions();
		model.addAttribute("transactions", transactions);
		return "transactions";
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	// show all contacts to choose one to transfer money ////////////////////////////////////////////////////////
//	@GetMapping("/transactions")
//	public String contacts(Model model) {
//		List<User> contacts = service.findAllContacts();
//		model.addAttribute("contacts", contacts);
//		return "transactions";
//	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
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
