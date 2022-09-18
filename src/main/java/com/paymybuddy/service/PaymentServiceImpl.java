package com.paymybuddy.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.paymybuddy.entities.BankAccount;
import com.paymybuddy.entities.Transaction;
import com.paymybuddy.entities.User;
import com.paymybuddy.repositories.BankAccountRepository;
import com.paymybuddy.repositories.FriendRepository;
import com.paymybuddy.repositories.TransactionRepository;
import com.paymybuddy.repositories.UserRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	private final UserRepository userRepo;
	private final BankAccountRepository accountRepo;
	private final FriendRepository friendRepo;
	private final TransactionRepository transactionRepo;
	
	public PaymentServiceImpl(UserRepository userRepo, BankAccountRepository accountRepo, FriendRepository friendRepo, TransactionRepository transactionRepo) {
		this.userRepo = userRepo;
		this.accountRepo = accountRepo;
		this.friendRepo = friendRepo;
		this.transactionRepo = transactionRepo;
	}
	
	public String userInfos() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String userInfoEmail = authentication.getName();
    	
    	return userInfoEmail;
    }

	
	// add a bank account to an user
	@Override
	public void addAccount() {
		
		User user = new User();
		String userMail = userInfos();
		
		user = userRepo.findByEmail(userMail);

		BankAccount account = new BankAccount();
		account.setAmount(0);
		account.setUserId(user);
		
		accountRepo.save(account);
		
	}
	
	// add amount from an previously added account
	@Override
	public void addAmountFromAccount(BankAccount dto, double amount) {
		
		User user = new User();
		String userMail = userInfos();
		
		user = userRepo.findByEmail(userMail);
		
		BankAccount account = new BankAccount();
		Long accountId = dto.getAccountId();
		account = accountRepo.findByAccountId(accountId);
		
		user.setAmount(user.getAmount() + amount);
		account.setAmount(account.getAmount() - amount);
		
		userRepo.save(user);
		accountRepo.save(account);
	}
	
	
	// find all accounts of connected user
	@Override
	public List<BankAccount> findAllAccounts(){
		
		User user = new User();
		String userMail = userInfos();
		
		user = userRepo.findByEmail(userMail);
		
		List<BankAccount> accounts = (List<BankAccount>) accountRepo.findAllByUserId(user.getUserId());
		
		return accounts.stream()
				.map((account) -> mapToAccounts(account))
				.collect(Collectors.toList());
	}
	
	// choose wich informations will be displayed from findAllAccounts() method
	private BankAccount mapToAccounts(BankAccount account) {
		BankAccount accounts = new BankAccount();
		accounts.setAccountId(account.getAccountId());
		
		return accounts;
	}

	
	// transfer money from connected user to selected contact
	@Override
	public void payContact(User dto, Transaction transactionDto) {
		
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
			userRepo.save(user);
			userRepo.save(contact);
			transactionRepo.save(transaction);
		} else {
			//TODO gérer erreur amount < 0
		}
	}

	
	//find all contacts from connected user
//	@Override
//	public List<User> findAllContacts() {
//		
//		User user = new User();
//		String userMail = userInfos();
//		
//		user = userRepo.findByEmail(userMail);
//
//		List<User> contacts = (List<User>) friendRepo.findAllByUserId(user.getUserId());
//		
//		return contacts.stream()
//                .map((contact) -> mapToUsers(contact))
//                .collect(Collectors.toList());
//	}

	
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
