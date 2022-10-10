package com.paymybuddy.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.entities.Transaction;
import com.paymybuddy.entities.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	Page<Transaction> findAllByUserId(User userId, Pageable page);

}
