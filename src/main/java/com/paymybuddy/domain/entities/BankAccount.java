package com.paymybuddy.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long AccountId;
	
	@Column(name = "amount")
	private double amount;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;

	
	public BankAccount() {
		
	}
	
	public Long getAccountId() {
		return AccountId;
	}
	
	public void setAccountId(Long AccountId) {
		this.AccountId = AccountId;
	}
	
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
