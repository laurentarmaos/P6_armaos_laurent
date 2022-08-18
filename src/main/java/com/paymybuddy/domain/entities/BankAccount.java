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
	protected Long Id;
	
	@Column(name = "amount")
	private double amount;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;

	
	public BankAccount() {
		
	}
	
	public Long getId() {
		return Id;
	}
	
	public void setId(Long Id) {
		this.Id = Id;
	}
	
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
