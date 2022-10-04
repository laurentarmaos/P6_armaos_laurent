package com.paymybuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	Transaction findAllByUserId(Long userId);

}
