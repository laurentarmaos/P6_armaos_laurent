package com.paymybuddy.service;

import java.util.List;

import com.paymybuddy.entities.Transaction;
import com.paymybuddy.entities.User;

public interface PaymentService {

	void addAmountFromAccount(double amount);
	
	void payContact(User dto, Transaction transactionDto);
	
	//List<User> findAllContacts();
	

	
	List<Transaction> findAllTransactions();
}
