package com.paymybuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entities.Friend;
import com.paymybuddy.entities.FriendIdentity;
import com.paymybuddy.entities.User;

public interface FriendRepository extends JpaRepository<Friend, FriendIdentity>{

	//User findAllByUserId(Long userId);
}
