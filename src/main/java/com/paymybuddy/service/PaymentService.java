package com.paymybuddy.service;

import java.util.List;

import com.paymybuddy.entities.BankAccount;
import com.paymybuddy.entities.Transaction;
import com.paymybuddy.entities.User;

public interface PaymentService {

	void addAccount();
	
	void addAmountFromAccount(BankAccount dto, double amount);
	
	void payContact(User dto, Transaction transactionDto);
	
	//List<User> findAllContacts();
	
	List<BankAccount> findAllAccounts();
	
	List<Transaction> findAllTransactions();
}
