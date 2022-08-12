package com.paymybuddy.service;

import com.paymybuddy.domain.dtos.UserCreate;

public interface UserService {
	void createUser(UserCreate user);
}
