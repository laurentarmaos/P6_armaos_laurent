package com.paymybuddy.service;

import java.util.List;

import com.paymybuddy.domain.entities.BankAccount;
import com.paymybuddy.domain.entities.Transaction;
import com.paymybuddy.domain.entities.User;

public interface PaymentService {

	void addAcount();
	
	void addAmountFromAccount(BankAccount dto, double amount);
	
	void payContact(User dto, Transaction transactionDto);
	
	List<User> findAllContacts();
	
	List<BankAccount> findAllAccounts();
}
