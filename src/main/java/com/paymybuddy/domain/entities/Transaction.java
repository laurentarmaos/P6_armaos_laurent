package com.paymybuddy.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long Id;
	
	@Column(name = "beneficiary_id")
	private Long beneficiary;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "commission")
	private double commission;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
	public Transaction() {
		
	}
	
	public Long getId() {
		return Id;
	}
	
	public void setId(Long Id) {
		this.Id = Id;
	}

	
	public Long getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(Long beneficiary) {
		this.beneficiary = beneficiary;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
