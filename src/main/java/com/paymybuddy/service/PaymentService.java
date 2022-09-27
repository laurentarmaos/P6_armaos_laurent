package com.paymybuddy.service;

import java.util.List;

import com.paymybuddy.entities.Transaction;
import com.paymybuddy.entities.User;

public interface PaymentService {

	void addAmountFromAccount(double amount);
	
	List<Transaction> findAllTransactions();

	void payContact(String contactMail, double amount, String description) throws Exception;
}
