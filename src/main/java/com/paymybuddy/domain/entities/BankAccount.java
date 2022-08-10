package com.paymybuddy.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount extends AbstractEntity{
	
	@Column(name = "amount")
	private double amount;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;

	
	public BankAccount() {
		
	}
	
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
