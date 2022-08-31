package com.paymybuddy.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	

	@NotEmpty
	@Column(name = "role_name")
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();
	
	public Role() {
		
	}


	public Long getRoleId() {
		return roleId;
	}


	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	@Override
	public String toString() {
		return "Role [Id=" + roleId + ", roleName=" + roleName + "]";
	}
	
	
}
