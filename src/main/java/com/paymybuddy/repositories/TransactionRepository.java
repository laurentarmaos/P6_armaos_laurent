package com.paymybuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.domain.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	Transaction findAllByUserId(Long UserId);

}
