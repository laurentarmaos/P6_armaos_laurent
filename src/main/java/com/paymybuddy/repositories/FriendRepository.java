package com.paymybuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.domain.entities.Friend;
import com.paymybuddy.domain.entities.FriendIdentity;
import com.paymybuddy.domain.entities.User;

public interface FriendRepository extends JpaRepository<Friend, FriendIdentity>{

	User findAllByUserId(Long userId);
}
