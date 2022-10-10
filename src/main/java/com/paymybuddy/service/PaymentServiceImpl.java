package com.paymybuddy.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public void addAmountFromAccount(User dto) {
		
		User user = new User();
		String userMail = userInfos();
		
		user = userRepo.findByEmail(userMail);
	
		user.setAmount(user.getAmount() + dto.getAmount());
		
		userRepo.save(user);
	}
	

	@Override
	public void addAmountToAccount(User dto) throws Exception {
		User user = new User();
		String userMail = userInfos();
		
		user = userRepo.findByEmail(userMail);
		
		if( (user.getAmount() - dto.getAmount()) < 0) {
			
			throw new Exception("not enough amount on your account to proceed !");
		}
	
		user.setAmount(user.getAmount() - dto.getAmount());
		
		userRepo.save(user);
	}

	
	// transfer money from connected user to selected contact
	@Override
	@Transactional
	public void payContact(User friend, Transaction dtoTransaction) throws Exception{
		
		double commission = 0.05;

		User user = new User();
		String userMail = userInfos();
		
		user = userRepo.findByEmail(userMail);
		
		
		if( (user.getAmount() - dtoTransaction.getAmount()) < 0) {
			
			throw new Exception("not enough amount on your account to proceed !");
		}
		
		
		user.setAmount(user.getAmount() -  dtoTransaction.getAmount() );
		
		User contact = new User();
		
		contact = userRepo.findByEmail(friend.getEmail());
		contact.setAmount(contact.getAmount() + ( dtoTransaction.getAmount() * (1 - commission)) );
		
		Transaction transaction = new Transaction();
		
		transaction.setAmount(dtoTransaction.getAmount());
		transaction.setBeneficiary(contact);
		transaction.setCommission(dtoTransaction.getAmount() * commission);
		
		LocalDate date = LocalDate.now();
		transaction.setDate(date);
		transaction.setDescription(dtoTransaction.getDescription());
		transaction.setUserId(user);
		
		userRepo.save(user);
		transactionRepo.save(transaction);
			
	}
	

	
//	// choose wich informations will be displayed from findAllContacts() method
//	private User mapToUsers(User user){
//        User users = new User();
//        users.setFirstName(user.getFirstName());
//        users.setLastName(user.getLastName());
//        return users;
//    }
	
	
	// find all transactions from connected user
	@Override
	public Page<Transaction> findAllTransactions(int pageNo, int pageSize) {

		
		String userMail = userInfos();
		User user = userRepo.findByEmail(userMail);
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<Transaction> transactionList = transactionRepo.findAllByUserId(user, pageable);

		return transactionList;
	}
	
	
//	//choose wich informations will be displayed from findAllTransactions() method
//	private Transaction mapToTransactions(Transaction transaction) {
//		Transaction transactions = new Transaction();
//		transactions.setBeneficiary(transaction.getBeneficiary());
//		transactions.setAmount(transaction.getAmount());
//		transactions.setDescription(transaction.getDescription());
//		return transactions;
//	}
}
