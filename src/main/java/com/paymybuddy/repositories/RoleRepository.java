package com.paymybuddy.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleName(String roleName);
}
