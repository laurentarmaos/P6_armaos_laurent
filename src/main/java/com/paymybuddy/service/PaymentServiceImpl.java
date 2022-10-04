package com.paymybuddy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.paymybuddy.entities.Transaction;
import com.paymybuddy.entities.User;
import com.paymybuddy.repositories.TransactionRepository;
import com.paymybuddy.repositories.UserRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	private final UserRepository userRepo;
	private final TransactionRepository transactionRepo;
	
	public PaymentServiceImpl(UserRepository userRepo, TransactionRepository transactionRepo) {
		this.userRepo = userRepo;
		this.transactionRepo = transactionRepo;
	}
	
	public String userInfos() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String userInfoEmail = authentication.getName();
    	
    	return userInfoEmail;
    }

	
	
	@Override
	public void addAmountFromAccount(double amount) {
		
		User user = new User();
		String userMail = userInfos();
		
		user = userRepo.findByEmail(userMail);
	
		user.setAmount(user.getAmount() + amount);
		
		userRepo.save(user);
	}
	


	
	// transfer money from connected user to selected contact
	@Override
	@Transactional
	public void payContact(String contactMail, double amount, String description) throws Exception{
		
		double commission = 0.05;

		User user = new User();
		String userMail = userInfos();
		
		user = userRepo.findByEmail(userMail);
		
		
		if( (user.getAmount() - amount) < 0) {
			
			throw new Exception("not enough amount on your account to proceed !");
		}
		
		
		user.setAmount(user.getAmount() -  amount );
		
		User contact = new User();
		
		contact = userRepo.findByEmail(contactMail);
		contact.setAmount(contact.getAmount() + ( amount * (1 - commission)) );
		
		Transaction transaction = new Transaction();
		
		transaction.setAmount(amount);
		transaction.setBeneficiary(contact);
		transaction.setCommission(amount * commission);
		
		LocalDate date = LocalDate.now();
		transaction.setDate(date);
		System.out.println(transaction.getDate());
		transaction.setDescription(description);
		transaction.setUserId(user);
		
		user.getTransactions().add(transaction);
			
		userRepo.save(user);
//		userRepo.save(contact);
//		transactionRepo.save(transaction);
			
	}
	



	
	// choose wich informations will be displayed from findAllContacts() method
	private User mapToUsers(User user){
        User users = new User();
        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());
        return users;
    }
	
	
	// find all transactions from connected user
	@Override
	public List<Transaction> findAllTransactions() {
		
		String userMail = userInfos();
		User user = userRepo.findByEmail(userMail);
		
		List<Transaction> transactions = (List<Transaction>) transactionRepo.findAllByUserId(user.getUserId());
		
		return transactions.stream()
				.map((transaction) -> mapToTransactions(transaction))
				.collect(Collectors.toList());
	}
	
	
	//choose wich informations will be displayed from findAllTransactions() method
	private Transaction mapToTransactions(Transaction transaction) {
		Transaction transactions = new Transaction();
		transactions.setBeneficiary(transaction.getBeneficiary());
		transactions.setAmount(transaction.getAmount());
		transactions.setDescription(transaction.getDescription());
		return transactions;
	}
}
