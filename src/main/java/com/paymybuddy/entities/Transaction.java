package com.paymybuddy.entities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	@Column(name = "beneficiary_id")
	private User beneficiary;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "commission")
	private double commission;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private User userId;
	
	public Transaction() {
		
	}
	
	public Long getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	
	public User getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(User beneficiary) {
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

	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	

}
