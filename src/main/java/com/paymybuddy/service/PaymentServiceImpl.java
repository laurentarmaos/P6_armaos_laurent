package com.paymybuddy.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	public void payContact(User dto, Transaction transactionDto) throws Exception{
		
		double commission = 0.05;

		User user = new User();
		String userMail = userInfos();
		
		user = userRepo.findByEmail(userMail);		
		user.setAmount(user.getAmount() - ( transactionDto.getAmount() * (1 - commission)) );
		
		User contact = new User();
		String contactMail = dto.getEmail();
		
		contact = userRepo.findByEmail(contactMail);
		contact.setAmount(contact.getAmount() + ( transactionDto.getAmount() * (1 + commission)) );
		
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDto.getAmount());
		transaction.setBeneficiary(contact);
		transaction.setCommission(transactionDto.getAmount() * commission);
		transaction.setDate(new Date());
		transaction.setDescription(transactionDto.getDescription());
		transaction.setUserId(user);
		
		if( (user.getAmount() - transactionDto.getAmount()) >= 0) {	
			transactionRepo.save(transaction);
			userRepo.save(user);
			userRepo.save(contact);
		} else {
			throw new Exception("not enough amount on your account to proceed !");
		}
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
