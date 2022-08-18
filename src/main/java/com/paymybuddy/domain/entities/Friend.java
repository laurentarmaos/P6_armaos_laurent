package com.paymybuddy.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friend")
public class Friend {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long Id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_id_friend")
	private Long friendId;
	
	public Friend() {
		
	}
	
	public Long getId() {
		return Id;
	}
	
	public void setId(Long Id) {
		this.Id = Id;
	}
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}
	
	
	

}
