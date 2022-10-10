package com.paymybuddy.service;


import org.springframework.data.domain.Page;

import com.paymybuddy.entities.Transaction;
import com.paymybuddy.entities.User;

public interface PaymentService {

	void addAmountFromAccount(User dto);
	
	void addAmountToAccount(User dto) throws Exception;
	
	Page<Transaction> findAllTransactions(int pageNo, int pageSize);

	void payContact(User friend, Transaction transaction) throws Exception;
}
