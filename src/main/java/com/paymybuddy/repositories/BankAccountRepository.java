package com.paymybuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.domain.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{

	BankAccount findAllByUserId(Long userId);

	BankAccount findByAccountId(Long accountId);
}
