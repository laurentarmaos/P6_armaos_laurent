package com.paymybuddy.domain.dtos;

public class UserCreate {

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private double amount;
	
	public UserCreate() {
		
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public double getAmount() {
		return amount;
	}
	
	
}
