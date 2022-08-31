package com.paymybuddy.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.domain.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleName(String roleName);
}
