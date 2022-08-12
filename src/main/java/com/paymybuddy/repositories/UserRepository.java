package com.paymybuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
